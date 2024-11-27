package org.example.demo6;

public class Review {
    private String itemname ;
    private  String reviewtext;
    private int rating;
    public Review(String itemname, String reviewtext , int rating){
        this.itemname = itemname;
        this.rating = rating ;
        this.reviewtext = reviewtext;
    }
    public String toString(){
        return "Item: " + itemname + "|rating :" + rating + "|reviewss" + reviewtext;
    }
}
