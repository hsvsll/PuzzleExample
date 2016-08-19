package com.example.hs.com.puzzleexample.utils.utils;

import com.example.hs.com.puzzleexample.utils.entity.ImageItemBean;
import com.example.hs.com.puzzleexample.utils.main.PuzzleMain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huha on 2016/8/18.
 */
public class GameUtils {
    //游戏信息单元格Bean
    public static List<ImageItemBean> mItemBeans = new ArrayList<>();
    //空格单元格
    public static ImageItemBean mBlankItemBean = new ImageItemBean();

    /**
     * 判断点击的Item是否可移动
     * @param position
     * @return
     */
    public static boolean isMoveable(int position){
        int type = PuzzleMain.TYPE;
        //获取空格Item
        int blankId = GameUtils.mBlankItemBean.getmItemId() - 1;
        //不同行 相差为type
        if(Math.abs(blankId - position) == type){
            return true;
        }
        //同行，相差为 1
        if(blankId / type == position / type && Math.abs(blankId - position) ==1){
            return true;
        }
        return false;
    }


    /**
     * 交换 空格与点击Item的位置
     * @param from  交换图
     * @param blank 空白图
     */
    public static void swapItems(ImageItemBean from,ImageItemBean blank){
        ImageItemBean tempItemBean = new ImageItemBean();
        //交换BitmapId
        tempItemBean.setmBitmapId(from.getmBitmapId());
        from.setmBitmapId(blank.getmBitmapId());
        blank.setmBitmapId(tempItemBean.getmBitmapId());
        //交换bitmap
        tempItemBean.setmBitmap(from.getmBitmap());
        from.setmBitmap(blank.getmBitmap());
        blank.setmBitmap(tempItemBean.getmBitmap());
        //设置新的Blank
        GameUtils.mBlankItemBean = from;
    }


    /**
     * 生成随机的 Ttem
     */
    public static void getPuzzleGenerator(){
        int index = 0;
        //随机打乱顺序
        for(int i = 0;i < mItemBeans.size();i++){
            index = (int) (Math.random()*PuzzleMain.TYPE*PuzzleMain.TYPE);
            swapItems(mItemBeans.get(index), GameUtils.mBlankItemBean);
        }
        List<Integer> data = new ArrayList<>();
        for(int i= 0;i<mItemBeans.size();i++){
            data.add(mItemBeans.get(i).getmBitmapId());
        }
        //判断生成是否有解
        if(canSolve(data)){
            return;
        }else {
            getPuzzleGenerator();
        }
    }

    /**
     * 该数据是否有解
     * @param data 拼图数组数据
     * @return
     */
    private static boolean canSolve(List<Integer> data) {
        //获取空格Id
        int blankId = GameUtils.mBlankItemBean.getmItemId();
        //可行性原则
        if(data.size() % 2 ==1){
            return getInversion(data) % 2 ==0;
        }else {
            //从底往上数，空格位于奇数
            if(((blankId -1)/PuzzleMain.TYPE) % 2 ==1){
                return getInversion(data) % 2 == 0;
            }else{
                //从底部往上数，定位位于偶数行
                return getInversion(data)%2 == 1;
            }
        }
    }

    /**
     * 计算倒置和算法
     * @param data  拼图数组数据
     * @return      该序列的倒置和
     */
    private static int getInversion(List<Integer> data) {
        int inversions = 0;
        int inversionCount = 0;
        for(int i = 0;i<data.size();i++){
            for(int j = i+1;j<data.size();j++){
                int index = data.get(i);
                if(data.get(j) != 0 && data.get(j) < index){
                    inversionCount ++;
                }
            }
            inversions += inversionCount;
            inversionCount = 0;
        }
        return inversions;
    }

    /**
     * 是否拼图成功
     *
     * @return 是否拼图成功
     */
    public static boolean isSuccess() {
        for (ImageItemBean tempBean : GameUtils.mItemBeans) {
            if (tempBean.getmBitmapId() != 0 &&
                    (tempBean.getmItemId()) == tempBean.getmBitmapId()) {
                continue;
            } else if (tempBean.getmBitmapId() == 0 &&
                    tempBean.getmItemId() == PuzzleMain.TYPE * PuzzleMain.TYPE) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
