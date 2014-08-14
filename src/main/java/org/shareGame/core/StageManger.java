/**
 * 
 */
package org.shareGame.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 各个场景的统一管理器，用于保存当前的状态，以及将当前的状态序列化到文本中<br>
 * 能从文本中将保存的状态重新加载到游戏当中
 * @author ledkk
 *
 */
public class StageManger {
	/**
	 * 当前正在处理的场景的编号
	 */
	private int currentStageIndex = 0;
	
	/**
	 * 同时正在运行的场景
	 */
	private Set<Integer> daemonStages = new LinkedHashSet<Integer>();
	
	
	/**
	 * 当前加载的stage
	 */
	private List<Stage> stages = new LinkedList<Stage>();
	
	
	private final static  StageManger instance = new StageManger();
	
	
	private StageManger(){
	}
	
	public static StageManger get(){
		return instance;
	}
	
	public void addStage(Stage stage){
		stages.add(stage);
	}
	
	
	public void setCurrentStage(Stage stage){
		Stage curStage = stages.get(currentStageIndex);
		if(curStage != null) putDaemon(curStage);
		int index = stages.indexOf(stage);
		if(index != -1){
			currentStageIndex = index;
		}
	}
	
	
	public void putDaemon(Stage stage){
		int index = stages.indexOf(stage);
		daemonStages.add(index);
	}
	
	public void save(){
		try {
			File dir = new File("stage");
			if(! dir.exists() || !dir.isDirectory()){
				dir.mkdir();
			}
			
			File stageData = new File(dir,"stage.data");
			if(stageData.exists()){
				stageData.delete();
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(stageData));
			
			oos.writeObject(currentStageIndex);
			oos.writeObject(daemonStages);
			oos.writeObject(stages);
			oos.writeObject(WorldClock.get().getCurTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void load(){
		try {
			File dir = new File("stage");
			if(! dir.exists() || !dir.isDirectory()){
				dir.mkdir();
			}
			
			File stageData = new File(dir,"stage.data");
			if(!stageData.exists()){
				throw new RuntimeException("未找到stage文件");
			}
			ObjectInputStream oos = new ObjectInputStream(new FileInputStream(stageData));
			
			currentStageIndex = (Integer) oos.readObject();
			daemonStages =  (Set<Integer>) oos.readObject();
			stages = (List<Stage>) oos.readObject();
			WorldClock.get().setCurTime((Long) oos.readObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "StageManger [currentStageIndex=" + currentStageIndex
				+ ", daemonStages=" + daemonStages + ", stages=" + stages + "]";
	}
}
