package com.daystudy.daystudy.day6;

/**
 * Description: Buider模式
 * Author     : xq
 * Date       : 2017/6/1
 */

public class Teacher {

    private final String name;
    private final String gender;
    private final int age;
    private final int tel;

    private Teacher(Builder builder) {
        name = builder.name;
        gender = builder.gender;
        age = builder.age;
        tel = builder.tel;
    }


    public static final class Builder {
        private final String name;
        private final String gender;
        private int age;
        private int tel;

        public Builder(String name,String gender) {
            this.name = name;
            this.gender = gender;
        }

        public Builder age(int val) {
            age = val;
            return this;
        }

        public Builder tel(int val) {
            tel = val;
            return this;
        }

        public Teacher build() {
            return new Teacher(this);
        }
    }
}
