package csd;

class App {
	public static void main(String [] args)
	{
		CrapsSimulationApp.run();		
	}
}

class CrapsSimulationApp {
	public static void run()
	{
		java.util.Scanner kb = new java.util.Scanner(System.in);
		
		for (;;) {
			System.out.print("kaç keç oynatmak istersiniz:");
			int cnt = Integer.parseInt(kb.nextLine());
			
			if (cnt <= 0)
				break;
			
			CrapsSimulation simulation = new CrapsSimulation();
			
			simulation.run(cnt);
			
			System.out.printf("kazanma olasılığı:%f%n", simulation.p);
		}
	}
}

class CrapsSimulation {
	public double p;
	
	public void run(int count)
	{
		int winCount = 0;
		java.util.Random r = new java.util.Random();
		
		for (int i = 0; i < count; ++i) {
			Craps craps = new Craps(r);
			
			craps.play();
			
			if (craps.win)
				++winCount;
		}
		p = (double)winCount / count;
	}
}

class Craps {
	public boolean win;
	public java.util.Random random;
	
	public int roll()
	{
		return random.nextInt(1, 7) + random.nextInt(1, 7);
	}
	
	public void rollIndeterminate(int result)
	{
		int total;
		
		while ((total = roll()) != result && total != 7)
			;
		
		win = total == result;
	}
	
	public Craps(java.util.Random r)
	{
		random = r;
	}
	
	public void play()
	{		
		int total = roll();
		
		switch (total) {
		case 7, 11 -> win = true;
		case 2, 3, 12 -> win = false;
		default -> rollIndeterminate(total);
		}		
	}	
}



