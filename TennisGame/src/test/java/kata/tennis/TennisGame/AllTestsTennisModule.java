package kata.tennis.TennisGame;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	TennisSetTest.class,
	TennisGameTest.class,
	TennisMatchSet.class
})
public class AllTestsTennisModule {
	
}
