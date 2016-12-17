package net.rb.lei.util;

import net.rb.lei.entity.Mob;
import net.rb.lei.entity.Wave;

public class WaveManager {

	private Mob enemyType;
	private float timeSinceLastWave, timeBetweenEnemies;
	private int waveNumber, enemiesPerWave;
	private Wave currentWave;
	
	public WaveManager(Mob enemyType, float timeBetweenEnemies, int enemiesPerWave) {
		this.enemyType = enemyType;
		this.timeBetweenEnemies = timeBetweenEnemies;
		this.enemiesPerWave = enemiesPerWave;
		this.waveNumber = 0;
		
		this.currentWave = null;
		
		newWave();
	}
	
	public void update() {
		if (!currentWave.isCompleted())
			currentWave.update();
		else
			newWave();
	}
	
	private void newWave() {
		currentWave = new Wave(enemyType, timeBetweenEnemies, enemiesPerWave);
		waveNumber++;
		System.out.println("Beginning Wave: " + waveNumber);
	}
	
	public Wave getCurrentWave() {
		return currentWave;
	}
}