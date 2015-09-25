package com.game.components;

public class Time {
		int division;
		private int minute,second;
		
		public Time(int division) {	
			 this.division = division;
			 minute = 0;
			 second = 0;
		}
		
		public int getMinutes() {
			return minute;
			
		}

		public void setMinutes(int minute) {
			this.minute = minute;
		}

		public int getSeconds() {
			return second;
		}

		public void setSeconds(int second) {
			this.second = second;
		}
		
		public void tick(int timeValue){
			if(timeValue <= 12000){
				if((timeValue%division) == 0){	

					if(second<60){
						second+=1;				
					}
					else{
						if(second==60){
							second=0;
							minute+=1;
						}
					}
				}
			}
		}
}
