package com.dealwallet.webdriver.selenium.Samles;


import java.io.FileOutputStream;

import java.io.IOException;

import jxl.Workbook;

import jxl.read.biff.BiffException;

import jxl.write.WritableWorkbook;

import jxl.write.WritableSheet;

import jxl.write.Label;

import jxl.write.WriteException;

public class WriteDatatoXLS
{


public static void main(String[] args) throws IOException, WriteException, BiffException{

//Declare the name of the Excel file

FileOutputStream exlFileName= new FileOutputStream("D:/testExcel.xls");

//making the instance of a Writable Excel workbook and giving it a Name

WritableWorkbook exlWorkBook = Workbook.createWorkbook(exlFileName);

//Creating Writable worksheets in the writable workbook

WritableSheet exlWorkSheet = exlWorkBook.createSheet("Sheet", 0);

//Creating content for a cell in the excel workbook

Label cellContent = new Label(0,0,"hai");

//Adding a cell and inserting content in the cell.

exlWorkSheet.addCell(cellContent);

//Confirm the writing in the excel i.e. Write in the excel workbook.

exlWorkBook.write();

//Close the workbook

exlWorkBook.close();

}//eof main

}//eof class

