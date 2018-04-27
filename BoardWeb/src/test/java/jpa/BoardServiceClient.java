package jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.yedam.app.board.BoardVO;

public class BoardServiceClient {
    public static void main(String[] args) {
        // EntityManager 생성
        EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("JPAProject");
        EntityManager em = emf.createEntityManager();
        
        // Transaction 생성
        EntityTransaction tx = em.getTransaction();
        try {
            // Transaction 시작
            tx.begin();

            BoardVO board = new BoardVO();
            board.setTitle("JPA 제목");
            board.setWriter("관리자");
            board.setContent("되지롱");
            board.setUploadFileName("test.txt");

            // 글 등록
            em.persist(board);

            // 글 목록 조회
            String jpql = "select b from BoardVO b order by b.seq desc";
            List<BoardVO> boardList = em.createQuery(jpql, 
            		BoardVO.class).getResultList();
            for (BoardVO brd : boardList) {
                System.out.println("---> " + brd.toString());
            }
            // Transaction commit
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Transaction rollback
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
