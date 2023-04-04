package Amazon.WebElements;

public class HomePageElements {
    public static final String searchBoxWebElement="//*[@id=\"twotabsearchtextbox\"]";
    public static final String searchButtonWebElement="//*[@id=\"nav-search-submit-button\"]";
    public static final String verifySearchedProductWebElement="//*[@id=\"search\"]/span/div/h1/div/div[1]/div/div/span[3]";
    public static final String scrollWebElement ="//*[@id='searchDropdownBox']";
    //public static final String searchScrollWebElement="//option[@value='search-alias=arts-crafts']";
    public static final String verifySearchScrollWebElement="//span[contains(text(),'hand sewing machine')and @class='a-color-state a-text-bold']";
    public static final String footerElements1="//table[@class='navFooterMoreOnAmazon']/tbody/tr//ancestor::td";
    public static final String footerElements2="//div[@id='nav-xshop']//ancestor::a";
    public static final String verifyFooterElements="//*[@id=\"main-content\"]/div/div/div/div/div[1]/div/div[6]/div/div/div[1]/div/div/div/div/div/div/div/div[1]/h1";
public static final String verifyEmailSignIn="//h4[contains(text(),'a problem')]";
public static final String SignInWindowElement="//*[@id=\"nav-link-accountList-nav-line-1\"]";
    public static final String emailInputBox1="ap_email";
    public static final String continueElement="continue";
    public static final String searchElement="//div/div[@id='search']/div/div/div/span/div/div[@class='sg-col-4-of-24 sg-col-4-of-12 s-result-item s-asin sg-col-4-of-16 sg-col s-widget-spacing-small sg-col-4-of-20'][3]/div/div/div/div/div[@class='a-section a-spacing-small puis-padding-left-small puis-padding-right-small']/div/h2/a";
public static final String verifyCart="//span[@id='nav-cart-count']";
public static final String signOutElement="//*[@id='hmenu-content']/ul[@class='hmenu hmenu-visible']/li/a[contains(text(),'Sign Out')]";
public static final String verifySignOut="//h1[contains(text(),'Sign in')]";
public static final String hoverOver="//a[@href='/prime?ref_=nav_cs_primelink_nonmember']";
public static final String elementHover="//a[@href='/gp/prime/?ref=nav_menu_greenbg_rocketman_unrec_cta']";
}
