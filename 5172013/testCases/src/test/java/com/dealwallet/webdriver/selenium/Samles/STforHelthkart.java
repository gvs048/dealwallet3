package com.dealwallet.webdriver.selenium.Samles;

import java.sql.Connection;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class STforHelthkart {
	String url="";
	String catname="";
	String iquery="";
	String attr= "";
	String[] items= null;
	String img="";
	String pname="";
	int price=0;
	String pprice="0";
	String asin="";
	String pgroup="";
	String purl="";
	int n=1;
	long nval;
	String pagelogic="";
	int rows=0;
	Element edesc=null;
	Elements body;
	Elements bodydesc;
	Document doc;
	Connection conn=null;
	int i;


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the catname
	 */
	public String getCatname() {
		return catname;
	}
	/**
	 * @param catname the catname to set
	 */
	public void setCatname(String catname) {
		this.catname = catname;
	}
	/**
	 * @return the iquery
	 */
	public String getIquery() {
		return iquery;
	}
	/**
	 * @param iquery the iquery to set
	 */
	public void setIquery(String iquery) {
		this.iquery = iquery;
	}
	/**
	 * @return the attr
	 */
	public String getAttr() {
		return attr;
	}
	/**
	 * @param attr the attr to set
	 */
	public void setAttr(String attr) {
		this.attr = attr;
	}
	/**
	 * @return the items
	 */
	public String[] getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(String[] items) {
		this.items = items;
	}
	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}
	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}
	/**
	 * @param pname the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return the pprice
	 */
	public String getPprice() {
		return pprice;
	}
	/**
	 * @param pprice the pprice to set
	 */
	public void setPprice(String pprice) {
		this.pprice = pprice;
	}
	/**
	 * @return the asin
	 */
	public String getAsin() {
		return asin;
	}
	/**
	 * @param asin the asin to set
	 */
	public void setAsin(String asin) {
		this.asin = asin;
	}
	/**
	 * @return the pgroup
	 */
	public String getPgroup() {
		return pgroup;
	}
	/**
	 * @param pgroup the pgroup to set
	 */
	public void setPgroup(String pgroup) {
		this.pgroup = pgroup;
	}
	/**
	 * @return the purl
	 */
	public String getPurl() {
		return purl;
	}
	/**
	 * @param purl the purl to set
	 */
	public void setPurl(String purl) {
		this.purl = purl;
	}
	/**
	 * @return the n
	 */
	public int getN() {
		return n;
	}
	/**
	 * @param n the n to set
	 */
	public void setN(int n) {
		this.n = n;
	}
	/**
	 * @return the nval
	 */
	public long getNval() {
		return nval;
	}
	/**
	 * @param nval the nval to set
	 */
	public void setNval(long nval) {
		this.nval = nval;
	}
	/**
	 * @return the pagelogic
	 */
	public String getPagelogic() {
		return pagelogic;
	}
	/**
	 * @param pagelogic the pagelogic to set
	 */
	public void setPagelogic(String pagelogic) {
		this.pagelogic = pagelogic;
	}
	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * @return the edesc
	 */
	public Element getEdesc() {
		return edesc;
	}
	/**
	 * @param edesc the edesc to set
	 */
	public void setEdesc(Element edesc) {
		this.edesc = edesc;
	}
	/**
	 * @return the doc
	 */
	public Document getDoc() {
		return doc;
	}
	/**
	 * @param doc the doc to set
	 */
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	/**
	 * @return the conn
	 */
	public Connection getConn() {
		return conn;
	}
	/**
	 * @param conn the conn to set
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	/**
	 * @return the body
	 */
	public Elements getBody() {
		return body;
	}
	/**
	 * @param body the body to set
	 */
	public void setBody(Elements body) {
		this.body = body;
	}
	/**
	 * @return the bodydesc
	 */
	public Elements getBodydesc() {
		return bodydesc;
	}
	/**
	 * @param bodydesc the bodydesc to set
	 */
	public void setBodydesc(Elements bodydesc) {
		this.bodydesc = bodydesc;
	}
	/**
	 * @return the i
	 */
	public int getI() {
		return i;
	}
	/**
	 * @param i the i to set
	 */
	public void setI(int i) {
		this.i = i;
	}
}
