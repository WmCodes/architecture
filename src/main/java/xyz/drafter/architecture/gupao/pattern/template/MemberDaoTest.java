package xyz.drafter.architecture.gupao.pattern.template;

import xyz.drafter.architecture.gupao.pattern.template.dao.MemberDao;

/**
 * @author wangmeng
 * @date 2019/8/25
 * @desciption
 */
public class MemberDaoTest {

    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao();
        memberDao.query();

    }
}
