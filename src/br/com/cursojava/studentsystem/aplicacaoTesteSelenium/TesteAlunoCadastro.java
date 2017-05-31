package br.com.cursojava.studentsystem.aplicacaoTesteSelenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlunoCadastro{

	/**
	 * PRIMEIRO TESTE AUTOMATIZADO, EXEMPLO!
	 */
	/*	@Test
		public void testandoAluno() throws InterruptedException {
	
			System.setProperty( "webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe" );
			WebDriver driver = new ChromeDriver();
			driver.get( "http://google.com.br/" );
			WebElement campoPesquisa = driver.findElement( By.name( "q" ) );
			campoPesquisa.sendKeys( "DJ André Duarte" );
			campoPesquisa.submit();
	
			Thread.sleep( 5000 );
			driver.close();
		}*/

	@Test
	public void testandoAluno() throws InterruptedException {

		System.setProperty( "webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe" );
		WebDriver driver = new ChromeDriver();
		driver.get( "http://localhost:8080/studentsystem/paginas/principal.jsp" );
		driver.findElement( By.xpath( "//*[@id='side-menu']/li[2]/a" ) ).click();
		driver.findElement( By.xpath( "//*[@id='side-menu']/li[2]/a/span" ) ).click();
		driver.findElement( By.xpath( "//*[@id='side-menu']/li[2]/ul/li[2]/a" ) ).click();

		WebElement campoNome = driver.findElement( By.name( "marca" ) );
		campoNome.sendKeys( "GTS" );

		driver.findElement( By.name( "modalidade" ) ).sendKeys( "Corrida" );

		driver.findElement( By.name( "cor" ) ).sendKeys( "Preta e Branca" );

		driver.findElement( By.name( "material" ) ).sendKeys( "Aluminio" );

		driver.findElement( By.xpath( "//*[@id='marcha']/option[2]" ) ).click();

		driver.findElement( By.name( "preco" ) ).sendKeys( "700,00" );

		driver.findElement( By.name( "dataFabricacao" ) ).sendKeys( "17/05/2017" );
		driver.findElement( By.xpath( "//*[@id='aluno']/option[2]" ) ).click();

		Thread.sleep( 10000 );
		driver.findElement( By.id( "inserir" ) ).click();

		boolean achouMarca = driver.getPageSource().contains( "GTS" );
		Assert.assertTrue( achouMarca );
		Thread.sleep( 10000 );
		driver.close();
	}
}
