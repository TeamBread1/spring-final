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

		// 현재 패키지의 workspace 경로 window는 chromedriver.exe 파일 있는 경로
		String WEB_DRIVER_ID = "webdriver.chrome.driver"; // 드라이버 ID
		String WEB_DRIVER_PATH = "C:\\projects\\chromedriver.exe";

		// 드라이버 설정
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		// ChromeDriver 옵션 설정
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--start-maximized"); // 전체화면으로 실행
		options.addArguments("--disable-popup-blocking"); // 팝업 무시
		options.addArguments("--disable-default-apps");

		// 위에서 설정한 드라이버 옵션을 넣은 드라이버 객체 생성
		// 옵션을 설정하지 않았을 때는 생략이 가능
		WebDriver mainDriver = new ChromeDriver(options);

		// 이동을 원하는 url = 처음에 크롤링 시작할 페이지 인듯
//		String url = "https://www.gompyo.net:444/bbangmap_list.php";
		String url = "https://www.mangoplate.com/search/%EB%B9%B5%EC%A7%91?keyword=%EB%B9%B5%EC%A7%91&page=";

		for (int i = 1; i < 11; i++) {

			// 웹 페이지 요청
			mainDriver.get(url + i);

			// 브라우저 이동시 생기는 로드시간을 기다린다.
			// HTTP응답속도보다 자바의 컴파일 속도가 더 빠르기 때문에 임의적으로 1초를 대기한다.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			// 1. 망고 플레이트 검색 시 대표 thumb에 접근한다.
			// WebElement는 html의 태그를 갖는 클래스이다.
			List<WebElement> thumbElement = mainDriver.findElements(By.className("only-desktop_not"));

			// 3. 상세페이지 주소로 접속한다.
			WebDriver driver = new ChromeDriver(options);

			for (int j = 0; j < thumbElement.size() / 2; j++) {
				// 2. thumb정보에서 상세페이지주소를 가져온다.
				String detailPageUrl = thumbElement.get(j).getAttribute("href");

				driver.get(detailPageUrl);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				// 4. 사진 이미지가 담겨 있는 태그를 가져온다.
				List<WebElement> imgElements = driver.findElements(By.className("owl-item"));
				List<String> imgList = new ArrayList<String>();
				// 4-1. 이미지가 담겨있는 img 태그에 접근한다.
				for (WebElement imgElement : imgElements) {
					String image = imgElement.findElement(By.tagName("img")).getAttribute("src");
					imgList.add(image.substring(0, image.indexOf("?")));
				}

				// 5. 게시글의 타이틀과 별점을 가져온다. // 타이틀 가져오기
				WebElement headerElement = driver.findElement(By.tagName("header"));
				String title = headerElement.findElement(By.className("restaurant_name")).getText();
				System.out.println("게시글 타이틀 : " + title);

				// 좋아요 가져오기
				WebElement statusElement = headerElement.findElement(By.className("status"));
				List<WebElement> likeElement = statusElement.findElements(By.tagName("span"));
				String like = likeElement.get(2).getText().replace(",", "");
				System.out.println("게시글 좋아요 수 : " + like);

				// 5. 상세페이지에서 리뷰를 가져온다.
				WebElement commentElement = driver.findElement(By.className("RestaurantReviewList__ReviewList"));

				// 5-1. 첫번째 리뷰를 가져온다.
				WebElement firstCommentElement = commentElement.findElement(By.tagName("li"));

				// 5-2. 리뷰 중 닉네임명이 있는 태그, 리뷰 내용이 있는 태그를 가져온다.
				List<WebElement> dataElements = firstCommentElement.findElements(By.tagName("div"));

				// 5-3. 닉네임이 있는 태그에서 닉네임을 가져온다.
				String author = dataElements.get(0).findElement(By.tagName("span")).getText();
				System.out.println("게시글 작성자 : " + author);

				// 5-4. 리뷰 내용이 있는 태그에서 리뷰 내용을 가져온다.
				String content = dataElements.get(2).findElement(By.tagName("p")).getText();
				System.out.println("게시글 내용 : " + content);
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
			// 상세페이지 접속 종료
			try {
				// 드라이버가 null이 아니라면
				if (driver != null) {
					// 드라이버 연결 종료
					driver.close(); // 드라이버 연결 해제

					// 프로세스 종료
					driver.quit();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}

		}
	}
}
