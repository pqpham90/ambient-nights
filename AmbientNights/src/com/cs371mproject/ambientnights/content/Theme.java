package com.cs371mproject.ambientnights.content;

public class Theme {

	private String name;
	private String track1;
	private String track2;
	
	public Theme(String n)
	{
		name = n;
		track1 = n + "1.mp3";
		track2 = n + "2.mp3";
	}
	
	public String getName(){
		return name;
	}
	
	public String getTrack1(){
		return track1;
	}
	
	public String getTrack2(){
		return track2;
	}
}
