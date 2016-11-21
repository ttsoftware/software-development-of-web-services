package services;

import models.CustomDate;

/**
 * Created by troels on 11/20/16.
 */
public class DateToTime {
    public static void main(String[] args){

        System.out.println(new CustomDate(2016, 11, 7).toDate().getTime());
        System.out.println(new CustomDate(2016, 11, 12).toDate().getTime());
        System.out.println(new CustomDate(2016, 11, 20).toDate().getTime());
    }
}
