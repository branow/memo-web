package com.branow.memoweb.scorecalculator.simple;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.branow.memoweb.scorecalculator.Score;
import com.branow.memoweb.scorecalculator.ScoreCalculator;
import com.branow.memoweb.scorecalculator.ScoreFullParams;
import com.branow.memoweb.scorecalculator.ScoreParams;

public class SimpleScoreCalculator implements ScoreCalculator {

    private final long currentTime;

    public SimpleScoreCalculator() {
        this.currentTime = toSeconds(LocalDateTime.now());
    }

    public SimpleScoreCalculator(LocalDateTime currentTime) {
        this.currentTime = toSeconds(currentTime);
    }

    @Override
    public int calcScore(ScoreParams params) {
        int score = params.getLastScore().getScore();
        long lastTime = toSeconds(params.getLastScore().getTime());
        long resetTime = toSeconds(params.getResetTime());
        if (lastTime > resetTime)
            throw new IllegalStateException("Last study type cannot be after reset time");
        double interval = resetTime - lastTime;
        double passed = currentTime - lastTime;
        double passedMp = Math.max((interval - passed) / interval, 0);
        return (int) (score * passedMp);
    }

    @Override
    public ScoreFullParams calcScoreParams(Score score) {
        SimpleScoreParams newParams = new SimpleScoreParams();
        newParams.setLastScore(score);
        newParams.setStudyRepetition(1);
        newParams.setResetTime(calcResetTime(score));
        return newParams;
    }

    @Override
    public ScoreFullParams calcScoreParams(Score score, ScoreFullParams params) {
        SimpleScoreParams newParams = new SimpleScoreParams();
        newParams.setLastScore(score);
        newParams.setStudyRepetition(params.getStudyRepetition() + 1);
        newParams.setResetTime(calcResetTime(score, params));
        return newParams;
    }


    private LocalDateTime calcResetTime(Score score) {
        int newInterval = calcInterval(score.getScore(), 0, 0, 0);
        long newResetTime = currentTime + newInterval;
        return validRestTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(newResetTime), ZoneId.systemDefault()), score);
    }

    private LocalDateTime calcResetTime(Score score, ScoreFullParams params) {
        long last = toSeconds(params.getLastScore().getTime());
        long reset = toSeconds(params.getResetTime());
        if (last > reset) {
            last = reset;
        }
        int interval = (int) (reset - last);
        int passedTime = (int) (currentTime - last);
        int newInterval = calcInterval(score.getScore(), interval, passedTime, params.getStudyRepetition());
        long newResetTime = currentTime + newInterval;
        return validRestTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(newResetTime), ZoneId.systemDefault()), score);
    }

    private LocalDateTime validRestTime(LocalDateTime restTime, Score score) {
        if (restTime.isBefore(score.getTime())) {
            System.out.println("RESET TIME is Before last STUDY TIME: reset=" + restTime +
                    ", study=" + score.getTime() + ", score=" + score.getScore());
            return score.getTime();
        }
        return restTime;
    }


    private int calcInterval(int score, int lastInterval, int passedTime, int studyRepetition) {
        double interval = calcDirectInterval(lastInterval);
        if (lastInterval != 0) {
            interval *= calcPassedTimeMultiplier(lastInterval, passedTime);
            interval += (lastInterval * 0.6);
        }
        interval *= calcScoreMultiplier(score);
        interval *= calcStudyRepetitionMultiplier(studyRepetition);
        return (int) interval;
    }

    private double calcPassedTimeMultiplier(int lastInterval, int passedTime) {
        return Math.min((double) passedTime / lastInterval, 1.5);
    }

    private double calcStudyRepetitionMultiplier(int studyRepetition) {
        return Math.min(1 + studyRepetition / 100., 1.8);
    }

    private double calcScoreMultiplier(int score) {
        if (score < 0 || score > 100)
            throw new IllegalArgumentException("Score must be in scope [0, 100]: " + score);
        return Math.pow(score / 100., 7) * 1.43;
    }

    private int calcDirectInterval(int lastInterval) {
        double last = Math.pow(lastInterval, 0.25);
        double var = 2.34 + 1.24 * last + 0 + 0 + -5.4E-7 * Math.pow(last, 4);
        return (int) Math.pow(var, 4);
    }

    private long toSeconds(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

}
