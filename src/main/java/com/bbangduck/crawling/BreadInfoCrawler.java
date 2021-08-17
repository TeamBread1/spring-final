package com.bbangduck.crawling;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbangduck.community.Board;
import com.bbangduck.community.BoardRepository;

@Component
public class BreadInfoCrawler {

	private BoardRepository repo;

	@Autowired
	public BreadInfoCrawler(BoardRepository repo) {
		this.repo = repo;
	}

//	@Scheduled(fixedRate = 1000 * 60 * 30)
	public void crawlBreadData() {

		// ���� ��Ű���� workspace ��� window�� chromedriver.exe ���� �ִ� ���
		String WEB_DRIVER_ID = "webdriver.chrome.driver"; // ����̹� ID
		String WEB_DRIVER_PATH = "C:\\projects\\chromedriver.exe";

		// ����̹� ����
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		// ChromeDriver �ɼ� ����
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--start-maximized"); // ��üȭ������ ����
		options.addArguments("--disable-popup-blocking"); // �˾� ����
		options.addArguments("--disable-default-apps");

		// ������ ������ ����̹� �ɼ��� ���� ����̹� ��ü ����
		// �ɼ��� �������� �ʾ��� ���� ������ ����
		WebDriver mainDriver = new ChromeDriver(options);

		// �̵��� ���ϴ� url = ó���� ũ�Ѹ� ������ ������ �ε�
//		String url = "https://www.gompyo.net:444/bbangmap_list.php";
		String url = "https://www.mangoplate.com/search/%EB%B9%B5%EC%A7%91?keyword=%EB%B9%B5%EC%A7%91&page=";

		for (int i = 1; i < 11; i++) {

			// �� ������ ��û
			mainDriver.get(url + i);

			// ������ �̵��� ����� �ε�ð��� ��ٸ���.
			// HTTP����ӵ����� �ڹ��� ������ �ӵ��� �� ������ ������ ���������� 1�ʸ� ����Ѵ�.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			// 1. ���� �÷���Ʈ �˻� �� ��ǥ thumb�� �����Ѵ�.
			// WebElement�� html�� �±׸� ���� Ŭ�����̴�.
			List<WebElement> thumbElement = mainDriver.findElements(By.className("only-desktop_not"));

			// 3. �������� �ּҷ� �����Ѵ�.
			WebDriver driver = new ChromeDriver(options);

			for (int j = 0; j < thumbElement.size() / 2; j++) {
				// 2. thumb�������� ���������ּҸ� �����´�.
				String detailPageUrl = thumbElement.get(j).getAttribute("href");

				driver.get(detailPageUrl);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				// 4. ���� �̹����� ��� �ִ� �±׸� �����´�.
				List<WebElement> imgElements = driver.findElements(By.className("owl-item"));
				List<String> imgList = new ArrayList<String>();
				// 4-1. �̹����� ����ִ� img �±׿� �����Ѵ�.
				for (WebElement imgElement : imgElements) {
					String image = imgElement.findElement(By.tagName("img")).getAttribute("src");
					imgList.add(image.substring(0, image.indexOf("?")));
				}

				// 5. �Խñ��� Ÿ��Ʋ�� ������ �����´�. // Ÿ��Ʋ ��������
				WebElement headerElement = driver.findElement(By.tagName("header"));
				String title = headerElement.findElement(By.className("restaurant_name")).getText();
				System.out.println("�Խñ� Ÿ��Ʋ : " + title);

				// ���ƿ� ��������
				WebElement statusElement = headerElement.findElement(By.className("status"));
				List<WebElement> likeElement = statusElement.findElements(By.tagName("span"));
				String like = likeElement.get(2).getText().replace(",", "");
				System.out.println("�Խñ� ���ƿ� �� : " + like);

				// 5. ������������ ���並 �����´�.
				WebElement commentElement = driver.findElement(By.className("RestaurantReviewList__ReviewList"));

				// 5-1. ù��° ���並 �����´�.
				WebElement firstCommentElement = commentElement.findElement(By.tagName("li"));

				// 5-2. ���� �� �г��Ӹ��� �ִ� �±�, ���� ������ �ִ� �±׸� �����´�.
				List<WebElement> dataElements = firstCommentElement.findElements(By.tagName("div"));

				// 5-3. �г����� �ִ� �±׿��� �г����� �����´�.
				String author = dataElements.get(0).findElement(By.tagName("span")).getText();
				System.out.println("�Խñ� �ۼ��� : " + author);

				// 5-4. ���� ������ �ִ� �±׿��� ���� ������ �����´�.
				String content = dataElements.get(2).findElement(By.tagName("p")).getText();
				System.out.println("�Խñ� ���� : " + content);
				Board board = new Board();
				board.setPostTitle(title);
				board.setPostLike(Integer.parseInt(like));
				board.setPostAuthor(author);
				board.setPostContent(content);
				board.setPostImage(imgList.get(0));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date time = new Date();
				board.setPostDate(format.format(time));
				board.setPostPwd("1234");

				repo.save(board);

			}
			// �������� ���� ����
			try {
				// ����̹��� null�� �ƴ϶��
				if (driver != null) {
					// ����̹� ���� ����
					driver.close(); // ����̹� ���� ����

					// ���μ��� ����
					driver.quit();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}

		}
	}
}
