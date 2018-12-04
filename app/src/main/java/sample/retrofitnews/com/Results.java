package sample.retrofitnews.com;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class Results{

	@SerializedName("totalResults")
	private int totalResults;

	@SerializedName("status")
	private String status;



	@SerializedName("articles")
	private List<ArticlesItem> articles;

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	public void setArticles(List<ArticlesItem> articles){
		this.articles = articles;
	}

	public List<ArticlesItem> getArticles(){
		return articles;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"totalResults = '" + totalResults + '\'' + 
			",articles = '" + articles + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}