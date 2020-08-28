package models;

public class Weapon implements Item
{
  String name = null;

  @Override
  public String getName() {
    return name;
  }

  public Weapon(String name){
    this.name = name;
  }


}