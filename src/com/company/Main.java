package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 600;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {260, 250, 270, 400,500, 500, 300,220};
    public static int[] heroesDamage = {20, 15, 10, 0, 5, 30,25,20};

    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medical","Golem", "Lucky", "Berserk", "Thor"};
    public static int roundNumber;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefenceType);
    }

    public static void round() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        medical();
        Golem();
        Lucky();
        Berserk();
        printStatistics();
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(12); //0,1,2,3,4,5,6,7,8,9,10,11
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i <heroesHealth.length ; i++) {
            if(heroesHealth[i] > 0){
                allHeroesDead = false;
                break;
            }
        }
        if(allHeroesDead){
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println(roundNumber + " ROUND -------------------");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
    }
    public static void medical() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i==3){
                continue;
            }
            if (heroesHealth[i]>0 && heroesHealth[i]<100 && heroesHealth[3]>0){
                heroesHealth[i] += 100;
                System.out.println("медик вылечил" + heroesAttackType[i]);
                break;
            }
        }
    }
    public static void Golem(){
        for (int i = 0; i < heroesHealth.length ; i++) {
            if (heroesHealth[i]>0 && heroesHealth[4]>0 && heroesHealth[4]!=heroesHealth[i]){
                heroesHealth[4] -=bossDamage /5;
                heroesHealth[i]+= bossDamage/5;
                System.out.println("голем получает урон исходящего Босса");
            }


        }
    }
    public static void Lucky(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] >0 && heroesHealth[5]>0 &&heroesHealth[5] <heroesHealth[i]){
                heroesHealth[5] = bossDamage=0;
                System.out.println("укланился от босса");

            }

        }
    }
    public static void Berserk(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] >0 && heroesHealth[6] >0 && heroesHealth [6] >heroesHealth[i]){
                heroesHealth[6] -=bossDamage /2;
                System.out.println("возвращаемый урон");
            }

        }
    }
    public static void Thor(){

    }
}

