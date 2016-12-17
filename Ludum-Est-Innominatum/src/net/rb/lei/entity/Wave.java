package net.rb.lei.entity;

import static net.rb.lei.util.Clock.*;

import java.util.concurrent.CopyOnWriteArrayList;

import net.rb.lei.entity.mob.MobUFO;

public class Wave {

	private Mob mob;
	private CopyOnWriteArrayList<Mob> mobList;
	private float timeSinceLastSpawn, spawnTime;
	private int mobsPerWave,  mobsSpawned;
	private boolean waveCompleted;
	
	public Wave(Mob mob, float spawnTime, int mobsPerWave) {
		this.mob = mob;
		this.mobList = new CopyOnWriteArrayList<Mob>();
		this.spawnTime = spawnTime;
		this.timeSinceLastSpawn = 0;
		this.mobsPerWave = mobsPerWave;
		this.mobsSpawned = 0;
		this.waveCompleted = false;
		
		spawn();
	}
	
	public void update() {
		boolean allMobsDead = true;
		
		if (mobsSpawned < mobsPerWave) {
			timeSinceLastSpawn += delta();
			if (timeSinceLastSpawn > spawnTime) {
				spawn();
				timeSinceLastSpawn = 0;
			}
		}
		
		for (Mob m : mobList) {
			if (m.isAlive()) {		
				allMobsDead = false;
				m.update();
				m.render();
			} else
				mobList.remove(m);
		}
		
		if (allMobsDead)
			waveCompleted = true;
	}
	
	private void spawn() {
		mobList.add(new MobUFO(mob.getTexture(), mob.getStartTile(), mob.getTileGrid(), mob.getWidth(), mob.getHeight(), mob.getHealth(), mob.getSpeed()));
		mobsSpawned++;
	}
	
	public CopyOnWriteArrayList<Mob> getMobList() {
		return mobList;
	}
	
	public boolean isCompleted() {
		return waveCompleted;
	}
}