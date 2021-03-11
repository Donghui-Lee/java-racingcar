package study.step4;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import study.step4.model.Car;
import study.step4.model.Movement;
import study.step4.ui.ResultView;

/**
 * Racing Game Class
 *
 * 자동차 경주 클래스
 *
 */
public class Racing {
    private List<Car> carList;
    private Movement movement;
    private ResultView resultView;

    public Racing() {
        carList = new ArrayList<>();
        movement = new Movement();
        resultView = new ResultView();
    }

    public void setUp(String[] names) {
        IntStream.range(0, names.length).forEach(i -> carList.add(new Car(names[i])));
    }

    public List<Car> getCarList() {
        return this.carList;
    }

    public void run() {
        carList.forEach(car -> car.move(movement.stopAndMove()));
    }

    public void printRacingResult(String printToken) {
        resultView.printRacingResult(carList, printToken);
    }

    public void printRacingWinner(String message) {
        resultView.printRacingWinner(this.getWinners(), message);
    }

    public List<Car> getWinners() {
        int winnerPosition = carList.stream()
            .mapToInt(Car::getPosition)
            .max()
            .orElse(-1);

        return carList.stream()
            .filter(car -> car.getPosition() == winnerPosition)
            .collect(toList());
    }
}
