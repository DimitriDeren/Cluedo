package model;

public class Suspects implements Item
{
  String name = null;

  @Override
  public String getName() {
    return name;
  }

  public Suspects(String name){
    this.name = name;
  }

  @Override
  public String toString() { return name;}

}