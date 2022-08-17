package model.entities;

import java.util.List;
import java.util.Objects;

public class Driver implements Comparable<Driver> {
    private String name;
    private Integer points;

    public Driver() {
    }

    public Driver(String name, Integer points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public int newPoints(int result, int hotLap) {
        if (result == 1){
            points = 25;
            if(hotLap == 1){
                points += 1;
            }
        }
        if (result == 2){
            points = 18;
            if(hotLap == 1){
                points += 1;
            }
        }
        if (result == 3){
            points = 15;
            if(hotLap == 1){
                points += 1;
            }
        }
        if (result == 4){
            points = 12;
            if(hotLap == 1){
                points += 1;
            }
        }
        if (result == 5){
            points = 10;
            if(hotLap == 1){
                points += 1;
            }
        }
        if (result == 6){
            points = 8;
            if(hotLap == 1){
                points += 1;
            }
        }
        if (result == 7){
            points = 6;
            if(hotLap == 1){
                points += 1;
            }
        }
        if (result == 8){
            points = 4;
            if(hotLap == 1){
                points += 1;
            }
        }
        if (result == 9){
            points = 2;
            if(hotLap == 1){
                points += 1;
            }
        }
        if (result == 10){
            points = 1;
            if(hotLap == 1){
                points += 1;
            }
        }
        if (result > 10){
            points = 0;
        }

        return points;
    }

    public int totalPoint(int result, int hotLap) {
        this.points += newPoints(result, hotLap);
        return this.points;
    }

    public static Driver hasDriver(List<Driver> list, String name) {
        return list.stream().filter(x -> Objects.equals(x.getName(), name)).findFirst().orElse(null);
    }

    public int compareTo(Driver driver) {
        if (this.points > driver.getPoints()) {
            return -1;
        }
        if (this.points < driver.getPoints()) {
            return +1;
        }
        return 0;
    }
}