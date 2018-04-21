package com.easybuy.util;

public class Page {

	//总页数
	private int totalPageCount=0;
	//信息总条数
	private int totalCount=0;
	//pageSize默认值为10
	private int pageSize=10;
	//当前页的页码
	private int currentPageNo=1;
	//mybatis查询时，limit startIndex,pageSize
	private int startIndex=1;
	
	public Page(int totalCount){
		/*if(totalCount>0){
			this.totalCount=totalCount;
		}*/
		setTotalCount(totalCount);		
	}
	
	public int getTotalPageCount() {
		return totalPageCount;
	}
	/*public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}*/
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		if(totalCount>0){
			this.totalCount = totalCount;
			//设置总条数的时候，顺便计算总页数
			this.totalPageCount=
					this.totalCount % pageSize==0?(this.totalCount/pageSize):(this.totalCount/pageSize+1);
		}
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize>0){
			this.pageSize = pageSize;			
		}
	}
	public int getCurrentPageNo() {
		if(totalCount==0){
			return 0;
		}
		return currentPageNo;
	}
	
	public void setCurrentPageNo(int currentPageNo) {
		if(currentPageNo>0){
			this.currentPageNo = currentPageNo;	
			this.startIndex=(currentPageNo-1)*pageSize;
		}
	}

	public int getStartIndex() {
		return startIndex;
	}

		
}
