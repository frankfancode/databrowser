package com.frankfancode.databrowser;

/**
 * Created by frank on 17-3-12.
 */

public class Contact {

    //private variables
    int _id;
    String _name;
    String _phone_number;
    String _salary;
    String _bonus;
    String _home;
    String _age;
    String _hight;
    String _weight;
    String _gender;

    public int getid() {
        return _id;
    }

    public String getname() {
        return _name;
    }
    

    public String getphone_number() {
        return _phone_number;
    }

    public String getsalary() {
        return _salary;
    }

    public String getbonus() {
        return _bonus;
    }

    public String gethome() {
        return _home;
    }

    public String getage() {
        return _age;
    }

    public String gethight() {
        return _hight;
    }

    public String getweight() {
        return _weight;
    }

    public String getgender() {
        return _gender;
    }

    // Empty constructor
    public Contact(){

    }

    public Contact( String _name, String _phone_number, String _salary, String _bonus, String _home, String _age, String _hight, String _weight, String _gender) {

        this._name = _name;
        this._phone_number = _phone_number;
        this._salary = _salary;
        this._bonus = _bonus;
        this._home = _home;
        this._age = _age;
        this._hight = _hight;
        this._weight = _weight;
        this._gender = _gender;
    }

    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
}


