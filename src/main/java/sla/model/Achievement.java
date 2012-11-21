package sla.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Transient;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import sla.util.AuthUtil;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Achievement {
	String name;
	String alias;
	String description;
	int score;
	String category;
	private boolean hide = false;
	@Transient
	long acquired;
	@Transient
	Integer identified;
	@Transient
	static List<Achievement> achievementList;
	@Transient
	static HashMap<String,Achievement> achievementHashMap;
	public static Achievement get(String name){
		HashMap<String,Achievement> achieveHashMap=getAchieveHashMap();
		return achieveHashMap.get(name);
	}
	public static HashMap<String, Achievement> getAchieveHashMap() {
		if(achievementHashMap==null){
			List<Achievement> achievementList=getAchievementList();
			achievementHashMap=new HashMap<String,Achievement>();
			for(int i=0;i<achievementList.size();i++){
				Achievement achievement=achievementList.get(i);
				achievementHashMap.put(achievement.getName(), achievement);
			}
			return achievementHashMap;
		}else{
			return achievementHashMap;
		}
	}
	public static List<Achievement> getAchievementList() {
		if(achievementList==null){
			return entityManager()
					.createQuery(
							"SELECT o FROM Achievement o WHERE o.hide != true",
							Achievement.class)
					.getResultList();
		}else{
			return achievementList;
		}
	}
	public static void addAchievementToUser(long userId,Achievement achievement){
		if(achievement!=null){
			if(!UserAchieve.existsAchieve(userId, achievement)){
				UserInfo userInfo=UserInfo.findUserInfo(userId);
				System.out.println("addAchievement:"+userInfo+"에 "+achievement+"추가");
				
				UserAchieve userAchieve=new UserAchieve();
				userAchieve.setUserInfo(userInfo);
				userAchieve.setAchievement(achievement);
				userAchieve.setAcquireDate(new Date());
				userAchieve.merge();
				userInfo.addAchieveScore(achievement.getScore());
				userInfo.merge();
			}
		}
	}
}
