//package com.demo_by_abhinav.currencyexchangeconverterapp.usecase
//
//import com.demo_by_abhinav.currencyexchangeconverterapp.ui.MainActivity
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//object static {
//
//}
//
//class RecyclerViewSampleTest {
//    @RunWith(MainActivity.class)
//    private static final int ITEM_BELOW_THE_FOLD = 40;
//
//    /**
//     * Use {@link ActivityScenario} to create and launch the activity under test. This is a
//     * replacement for {@link androidx.test.rule.ActivityTestRule}.
//     */
//    @Rule
//    public ActivityScenarioRule<MainActivity> activityScenarioRule =
//    new ActivityScenarioRule<MainActivity>(MainActivity.class);
//
//    @Test(expected = PerformException.class)
//            public void itemWithText_doesNotExist() {
//        // Attempt to scroll to an item that contains the special text.
//        onView(ViewMatchers.withId(R.id.recyclerView))
//            // scrollTo will fail the test if no item matches.
//            .perform(RecyclerViewActions.scrollTo(
//                hasDescendant(withText("not in the list"))
//            ));
//    }
//
//        @Test
//        public void scrollToItemBelowFold_checkItsText() {
//            // First scroll to the position that needs to be matched and click on it.
//            onView(ViewMatchers.withId(R.id.recyclerView))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, click()));
//
//            // Match the text in an item below the fold and check that it's displayed.
//            String itemElementText = getApplicationContext().getResources().getString(
//                R.string.item_element_text) + String.valueOf(ITEM_BELOW_THE_FOLD);
//            onView(withText(itemElementText)).check(matches(isDisplayed()));
//        }
//
//        @Test
//        public void itemInMiddleOfList_hasSpecialText() {
//            // First, scroll to the view holder using the isInTheMiddle matcher.
//            onView(ViewMatchers.withId(R.id.recyclerView))
//                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()));
//
//            // Check that the item has the special text.
//            String middleElementText =
//            getApplicationContext().getResources().getString(R.string.middle);
//            onView(withText(middleElementText)).check(matches(isDisplayed()));
//        }
//
//                /**
//                 * Matches the {@link CustomAdapter.ViewHolder}s in the middle of the list.
//                 */
//                private static Matcher<CustomAdapter.ViewHolder> isInTheMiddle() {
//            return new TypeSafeMatcher<CustomAdapter.ViewHolder>() {
//                @Override
//                protected boolean matchesSafely(CustomAdapter.ViewHolder customHolder) {
//                    return customHolder.getIsInTheMiddle();
//                }
//
//                @Override
//                public void describeTo(Description description) {
//                    description.appendText("item in the middle");
//                }
//            };
//        }
//}