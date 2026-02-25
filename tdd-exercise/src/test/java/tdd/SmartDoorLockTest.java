package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tdd.api.SmartDoorLock;
import tdd.impl.SmartDoorLockImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartDoorLockTest {

    private static final int PIN = 1234;
    private static final int WRONG_PIN = 4321;
    private static final int THREE_DIGIT_PIN = 111;
    private static final int FIVE_DIGIT_PIN = 11111;
    private static final int NEGATIVE_PIN = -1111;

    private SmartDoorLock smartLock;

    private void setPinAndLock() {
        this.smartLock.setPin(PIN);
        this.smartLock.lock();
    }

    private void failEachAllowedAttempt() {
        for (int i = 0; i < this.smartLock.getMaxAttempts(); i++) {
            this.smartLock.unlock(WRONG_PIN);
        }
    }

    @BeforeEach
    void initTest() {
        this.smartLock = new SmartDoorLockImpl();
    }

    @Test
    public void testLockIsInitiallyUnlocked() {
        assertFalse(this.smartLock.isLocked());
    }

    @Test
    public void testLockingIsSuccessful() {
        setPinAndLock();
        assertTrue(this.smartLock.isLocked());
    }

    @Test
    public void testCorrectPinUnlocksTheLock() {
        setPinAndLock();
        this.smartLock.unlock(PIN);
        assertFalse(this.smartLock.isLocked());
    }

    @Test
    public void testWrongPinDoesNotUnlockTheLock() {
        setPinAndLock();
        this.smartLock.unlock(WRONG_PIN);
        assertTrue(this.smartLock.isLocked());
    }

    @Test
    public void testFailedAttemptsCountIncreasesWhenEnteringWrongPin() {
        setPinAndLock();
        this.smartLock.unlock(WRONG_PIN);
        assertEquals(1, this.smartLock.getFailedAttempts());
    }

    @Test
    public void testLockIsNotBlockedBeforeFailingEachAllowedAttempt() {
        setPinAndLock();
        for (int i = 0; i < this.smartLock.getMaxAttempts() - 1; i++) {
            this.smartLock.unlock(WRONG_PIN);
        }
        assertFalse(this.smartLock.isBlocked());
    }

    @Test
    public void testTooManyAttemptsBlockTheLock() {
        setPinAndLock();
        failEachAllowedAttempt();
        assertTrue(this.smartLock.isBlocked());
    }

    @Test
    public void testLockCannotBeUnlockedWhenBlocked() {
        setPinAndLock();
        failEachAllowedAttempt();
        this.smartLock.unlock(PIN);
        assertTrue(this.smartLock.isLocked());
    }

    @Test
    public void testLockRemainsBlockedOnceBlocked() {
        setPinAndLock();
        failEachAllowedAttempt();
        this.smartLock.unlock(PIN);
        assertTrue(this.smartLock.isBlocked());
    }

    @Test
    public void testLockIsUnlockedAfterReset() {
        setPinAndLock();
        failEachAllowedAttempt();
        this.smartLock.reset();
        assertFalse(this.smartLock.isLocked());
    }

    @Test
    public void testLockIsNotBlockedAfterReset() {
        setPinAndLock();
        failEachAllowedAttempt();
        this.smartLock.reset();
        assertFalse(this.smartLock.isBlocked());
    }

    @Test
    public void testPinCannotBeSetWhenLockIsLocked() {
        setPinAndLock();
        assertThrows(IllegalStateException.class, () -> this.smartLock.setPin(PIN));
    }

    @Test
    public void testPinCannotBeSetWhenLockIsBlocked() {
        setPinAndLock();
        failEachAllowedAttempt();
        assertThrows(IllegalStateException.class, () -> this.smartLock.setPin(PIN));
    }

    @Test
    public void testLockCannotBeLockedBeforeSettingPin() {
        assertThrows(IllegalStateException.class, () -> this.smartLock.lock());
    }

    @Test
    public void testPinCannotBeShorterThanFourDigits() {
        assertThrows(IllegalArgumentException.class, () -> this.smartLock.setPin(THREE_DIGIT_PIN));
    }

    @Test
    public void testPinCannotBeLongerThanFourDigits() {
        assertThrows(IllegalArgumentException.class, () -> this.smartLock.setPin(FIVE_DIGIT_PIN));
    }

    @Test
    public void testCannotSetANegativeNumberAsPin() {
        assertThrows(IllegalArgumentException.class, () -> this.smartLock.setPin(NEGATIVE_PIN));
    }

    @Test
    public void testUnlockingWhenAlreadyUnlockedHasNoEffect() {
        setPinAndLock();
        this.smartLock.unlock(PIN);
        this.smartLock.unlock(WRONG_PIN);
        assertEquals(0, this.smartLock.getFailedAttempts());
    }

}
