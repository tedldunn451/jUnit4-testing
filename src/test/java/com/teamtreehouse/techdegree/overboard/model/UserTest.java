package com.teamtreehouse.techdegree.overboard.model;


import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ted on 2/20/2017.
 */
public class UserTest {

    private Board scienceBoard;
    private User Moe;
    private User Larry;
    private User Curly;
    private Question question;
    private Answer answer;

    @Before
    public void setUp() throws Exception {
        scienceBoard = new Board("Great Scientific Minds");
        Moe = scienceBoard.createUser("Moe");
        Larry = scienceBoard.createUser("Larry");
        Curly = scienceBoard.createUser("Curly");
        question = Moe.askQuestion("Who invented calculus?");
        answer = Larry.answerQuestion(question, "Isaac Newton");

    }

    @Test
    public void upvotedQuestionIncreasesQuestionersReputationByFive() throws Exception {

        Curly.upVote(question);

        assertEquals(5, Moe.getReputation());
    }

    @Test
    public void upvotedAnswerIncreasesAnswerersReputationByTen() throws Exception {

        Moe.upVote(answer);

        assertEquals(10, Larry.getReputation());
    }

    @Test
    public void acceptedAnswerIncreasesAnswerersReputationByFifteen() throws Exception {

        Moe.acceptAnswer(answer);

        assertEquals(15, Larry.getReputation());
    }

    @Test
    public void downvotedAnswerReducesAnswerersReputationByOne() throws Exception {
        Answer answer2 = Curly.answerQuestion(question, "Amanda Hugginkiss");

        Larry.downVote(answer2);

        assertEquals(-1, Curly.getReputation());
    }

    @Test(expected = VotingException.class)
    public void userUpvotingTheirOwnQuestionNotAllowed() throws Exception {
        Moe.upVote(question);
    }

    @Test(expected = VotingException.class)
    public void userUpvotingTheirOwnAnswerNotAllowed() throws Exception {
        Larry.upVote(answer);
    }


}