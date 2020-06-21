package cn.wpin.algorithm;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @desc:设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
 *
 * postTweet(userId, tweetId): 创建一条新的推文
 * getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
 * follow(followerId, followeeId): 关注一个用户
 * unfollow(followerId, followeeId): 取消关注一个用户
 *
 * 力扣4-13 每日一题
 *
 *
 * @author wangpin
 */
public class Twitter {

    private Map<Integer, List<User>> userMap = new HashMap<>(32);

    private Map<Integer, List<Text>> userText = new HashMap<>(32);

    /**
     * Initialize your data structure here.
     */
    public Twitter() {

    }

    public static void main(String[] args) throws InterruptedException {
        Twitter twitter = new Twitter();
      //  twitter.postTweet(1, 1);
        System.out.println(twitter.getNewsFeed(1));
//        twitter.follow(2, 1);
////        twitter.postTweet(2, 6);
//        System.out.println(twitter.getNewsFeed(1));
//        twitter.unfollow(2, 1);
//        System.out.println(twitter.getNewsFeed(2));
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        //推文不存在，就创建一个存放推文的容器
        if (!userMap.containsKey(userId)) {
            List<User> userHashSet = new ArrayList<>();
            List<Text> textHashSet = new ArrayList<>();
            userHashSet.add(new User(userId, "", new HashSet<>()));
            textHashSet.add(new Text(tweetId, "", LocalDateTime.now()));
            userMap.put(userId, userHashSet);
            userText.put(userId, textHashSet);
        } else {
            userText.get(userId).add(new Text(tweetId, "", LocalDateTime.now()));
        }
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        if (!userMap.containsKey(userId)){
            return new ArrayList<>();
        }
        List<User> set = userMap.get(userId);
        List<Set<Integer>> flows = set.stream().map(User::getMyFlow).collect(Collectors.toList());
        //将自己算在内，所有的人
        Set<Integer> list = flows.get(0);
        list.add(userId);
        Set<Text> result = new HashSet<>();
        for (Integer flow : list) {
            if (userText.containsKey(flow)){
                result.addAll(userText.get(flow));
            }
        }
        return result.stream().sorted(Comparator.comparing(Text::getCreatedTime).reversed()).map(Text::getId).collect(Collectors.toList());
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            List<User> userHashSet = new ArrayList<>();
            Set<Integer> flow = new HashSet<>();
            flow.add(followeeId);
            userHashSet.add(new User(followerId, "", flow));
            userMap.put(followerId, userHashSet);
        } else {
            List<User> users = userMap.get(followerId);
            users.get(0).getMyFlow().add(followeeId);
        }
        List<User> set = userMap.get(followerId);
        List<Set<Integer>> flow = set.stream().filter(u -> u.uid == followerId).map(User::getMyFlow).collect(Collectors.toList());
        flow.get(0).add(followeeId);


    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            List<User> set = userMap.get(followerId);
            List<Set<Integer>> flow = set.stream().filter(u -> u.uid == followerId).map(User::getMyFlow).collect(Collectors.toList());
            flow.get(0).remove(followeeId);
        }

    }

    /**
     * 用户内部类
     */
    class User {
        /**
         * 用户id
         */
        int uid;

        /**
         * 用户名称
         */
        String name;

        /**
         * 我的关注列表
         */
        Set<Integer> myFlow;

        public User(int uid) {
            this.uid = uid;
        }

        public User(int uid, String name) {
            this.uid = uid;
            this.name = name;
        }

        public User(int uid, String name, Set<Integer> myFlow) {
            this.uid = uid;
            this.name = name;
            this.myFlow = myFlow;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<Integer> getMyFlow() {
            return myFlow;
        }

        public void setMyflow(Set<Integer> myFlow) {
            this.myFlow = myFlow;
        }
    }

    /**
     * 推文内部类
     */
    class Text {

        /**
         * 文档id
         */
        int id;

        /**
         * 文档内容
         */
        String context;

        /**
         * 文档创建时间
         */
        LocalDateTime createdTime;

        public Text(int id) {
            this.id = id;
        }

        public Text(int id, String context) {
            this.id = id;
            this.context = context;
        }

        public Text(int id, String context, LocalDateTime createdTime) {
            this.id = id;
            this.context = context;
            this.createdTime = createdTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public LocalDateTime getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(LocalDateTime createdTime) {
            this.createdTime = createdTime;
        }

    }
}