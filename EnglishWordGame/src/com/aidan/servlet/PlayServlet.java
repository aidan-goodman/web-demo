package com.aidan.servlet;

import com.aidan.dao.NotesDao;
import com.aidan.dao.UserDao;
import com.aidan.dao.WordDao;
import com.aidan.pojo.Notes;
import com.aidan.pojo.User;
import com.aidan.pojo.Word;
import com.aidan.util.Pagination;
import com.aidan.util.PlayPassingValues;
import com.aidan.util.ScorePassingValues;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "PlayServlet", urlPatterns = {"/playServlet"})
public class PlayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理编码
        request.setCharacterEncoding("utf-8");

        //获取type
        String type = request.getParameter("type");

        //获取难度
        String Difficulty = request.getParameter("Difficulty");

        WordDao wd = new WordDao();
        UserDao ud = new UserDao();

        //获取id
        String uid = request.getParameter("id");

        //根据id查询用户
        User u = ud.selectUserById(uid);

        //生成题目
        List<Word> listtrue = wd.selectOne(Difficulty);
        List<Word> listflase = wd.selectThree();
        List<Word> listall = new ArrayList<Word>();
        listall.addAll(listtrue);
        listall.addAll(listflase);

        //打乱list
        Collections.shuffle(listall);

        //初始化生成四个单词的数据
        if ("play".equals(type)) {
            //初始化允许的错误次数
            int sum = 3;

            NotesDao nd = new NotesDao();
            Notes n = new Notes();

            /*//生成一条初始记录
            n.setUid(u.getId());
            n.setWid(listtrue.get(0).getId());
            n.setFrequency(0);

            //添加记录
            nd.insertOne(n);*/

            //初始化积分
            ud.updateDifficulty(u, 0, Difficulty);

            //查看单词数据
            /*for (int i = 0; i < listall.size(); i++) {
                System.out.println(listall.get(i));
                System.out.println(i);
            }*/

            //题目生成成功
            if (listtrue != null && listflase != null) {
                //传值
                PlayPassingValues P = new PlayPassingValues();
                P.playPassingValues(request, response, ud.selectDifficulty(uid, Difficulty), listtrue, listall, u, sum, Difficulty);
            }
            //判断对错
        } else if ("judge".equals(type)) {
            //获取用户选择的汉语id，正确答案的id，剩余次数，单词难度（随机难度需要用）
            int fid = Integer.parseInt(request.getParameter("fid"));
            int tid = Integer.parseInt(request.getParameter("tid"));
            int sum = Integer.parseInt(request.getParameter("sum"));
            String dif = request.getParameter("dif");

            //选对
            if (fid == tid) {
                if (Difficulty.equals("随机")) {
                    if (dif.equals("简单")) {
                        //积分加一，更新数据库
                        ud.updateDifficulty(u, ud.selectDifficulty(uid, Difficulty) + 1, Difficulty);
                    } else if (dif.equals("中等")) {
                        //积分加二，更新数据库
                        ud.updateDifficulty(u, ud.selectDifficulty(uid, Difficulty) + 2, Difficulty);
                    } else if (dif.equals("困难")) {
                        //积分加三，更新数据库
                        ud.updateDifficulty(u, ud.selectDifficulty(uid, Difficulty) + 3, Difficulty);
                    }
                } else {
                    //积分加一，更新数据库
                    ud.updateDifficulty(u, ud.selectDifficulty(uid, Difficulty) + 1, Difficulty);
                }
                NotesDao nd = new NotesDao();

                //查询该用户是否有这个单词的游戏记录
                Notes n = nd.selectNotes(uid, request.getParameter("tid"));

                //如果有 正确次数加1
                if (n != null) {
                    nd.updateOnee(n, (n.getTrequency() + 1));
                    //如果没有 生成一条记录
                } else {
                    //添加记录
                    Notes nn = new Notes();
                    nn.setUid(u.getId());
                    nn.setWid(tid);
                    nn.setFrequency(0);
                    nn.setTrequency(1);
                    nd.insertOne(nn);
                }

                //题目生成成功
                if (listtrue != null && listflase != null) {
                    //传值
                    PlayPassingValues P = new PlayPassingValues();
                    P.playPassingValues(request, response, ud.selectDifficulty(uid, Difficulty), listtrue, listall, u, sum, Difficulty);
                }
                //选错
            } else {
                //允许的错误次数减1
                sum--;
                NotesDao nd = new NotesDao();

                //查询该用户是否有这个单词的游戏记录
                Notes n = nd.selectNotes(uid, request.getParameter("tid"));

                //如果有 错误次数加1
                if (n != null) {
                    nd.updateOne(n, (n.getFrequency() + 1));
                    //如果没有 生成一条记录
                } else {
                    //添加记录
                    Notes nn = new Notes();
                    nn.setUid(u.getId());
                    nn.setWid(tid);
                    nn.setFrequency(1);
                    nn.setTrequency(0);
                    nd.insertOne(nn);
                }

                //如果允许的错误次数为0 游戏结束
                if (sum <= 0) {
                    //生成用户积分排名
                    List<User> list = ud.rankAll(Difficulty);

                    //积分排名生成成功
                    if (list != null) {
                        //分页
                        Pagination p = new Pagination();
                        p.pagination(request);

                        //获取总页数
                        int countPage = ud.selectNum() / 15 + 1;

                        //传值
                        ScorePassingValues S = new ScorePassingValues();
                        S.scorePassingValues(request, response, countPage, list, u, Difficulty);
                    }
                    //如果允许的错误次数大于0 游戏继续
                } else {
                    //题目生成成功
                    if (listtrue != null && listflase != null) {
                        //传值
                        PlayPassingValues P = new PlayPassingValues();
                        P.playPassingValues(request, response, ud.selectDifficulty(uid, Difficulty), listtrue, listall, u, sum, Difficulty);
                    }
                }
            }
            //再来一局
        } else if ("oneMoreGame".equals(type)) {
            request.setAttribute("user", u);
            request.getRequestDispatcher("play-welcome.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
