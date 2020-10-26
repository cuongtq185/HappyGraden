package vn.com.unit.pageable;

import java.util.ArrayList;
import java.util.List;


public class PageRequest<T> {
	// current page
	private int page;
	//số dòng 
	private int limit;
	//visitable page
	private int maxPage = 7;
	//danh sach page
	private List<pageItem> items;
	//tổng trang
	private int totalPages;
	//số phần tử
	private int countAll;
	//trang bắt đầu
	private int start;
	//trang kết thúc
	private int end;
	 private List<T> data;
	//trang giữa
	private int half;
	private int preMaxPage;
	private int nextMaxPage;

	public PageRequest(int page, int limit, int countAll, int totalPages) {
		if(limit > 50) {
			limit = 50;
		}
		this.page = page;
		this.limit = limit;
		this.countAll = countAll;
		this.totalPages = totalPages;
		this.preMaxPage = getPreMaxPage();
		this.nextMaxPage = getNextMaxPage();

		this.half = getHalf();
		this.start = getStart();
		this.end = getEnd();
		this.items= getItems();
	}
	public PageRequest(int page, int limit, int countAll) {
		if(limit > 50) {
			limit = 50;
		}
		this.page = page;
		this.limit = limit;
		this.countAll = countAll;
		this.totalPages = (int) Math.ceil((double) countAll / (double) limit);
		this.preMaxPage = getPreMaxPage();
		this.nextMaxPage = getNextMaxPage();

		this.half = getHalf();
		this.start = getStart();
		this.end = getEnd();
		this.items= getItems();
	}
	public PageRequest(int page, int limit, int countAll,List<T> data) {
		if(limit > 50) {
			limit = 50;
		}
		this.data = data;
		this.page = page;
		this.limit = limit;
		this.countAll = countAll;
		this.totalPages = (int) Math.ceil((double) countAll / (double) limit);
		this.preMaxPage = getPreMaxPage();
		this.nextMaxPage = getNextMaxPage();

		this.half = getHalf();
		this.start = getStart();
		this.end = getEnd();
		this.items= getItems();
	}
	public int getHalf() {
		this.half = (int) Math.floor(maxPage / 2);

		return this.half;
	}

	public void setHalf(int half) {
		this.half = half;
	}

	public PageRequest(Integer page, Integer limit) {
		this.page = page;
		this.limit = limit;
	}

	public int getPreMaxPage() {
		this.preMaxPage = this.page > 1 ? this.page - 1 : -1;
		return this.preMaxPage;
	}

	public void setPreMaxPage(int preMaxPage) {
		this.preMaxPage = preMaxPage;
	}

	public int getNextMaxPage() {
		this.nextMaxPage = this.page < this.totalPages ? this.page + 1 : -1;
		return this.nextMaxPage;
	}

	public void setNextMaxPage(int nextMaxPage) {
		this.nextMaxPage = nextMaxPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public List<pageItem> getItems() {
          if (this.maxPage > this.totalPages) {
        	  this.maxPage  = this.totalPages;
          }
		if (this.start <= 0) {
			this.start = 1;
			this.end = this.maxPage;
		}
		if (this.end > this.totalPages) {
			start = this.totalPages - this.maxPage + 1;
			end = this.totalPages;
		}


		List<pageItem> myList = new ArrayList<pageItem>();


		for (int i = start; i <= end; i++) {
			try {
				myList.add(new pageItem(i, i == this.page));


			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		this.items = myList;
		return this.items;
	}

	public void setItems(List<pageItem> items) {
		this.items = items;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCountAll() {
		return countAll;
	}

	public void setCountAll(int countAll) {
		this.countAll = countAll;
	}

	public int getStart() {
		this.start = page - this.half + 1 - maxPage % 2;
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		this.end = this.page + this.half;
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}


	public Integer getPage() {
		return this.page;
	}


	public Integer getOffset() {
		if (this.page > 0 || this.limit > 0) {
			int offset = (this.page - 1) * this.limit;
			return offset;
		}
		return null;
	}


	public Integer getLimit() {
		return this.limit;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}

}