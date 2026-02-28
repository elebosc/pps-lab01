package tdd.impl;

import tdd.api.SmartDoorLock;

/**
 * This class implements a smar door lock
 */
public class SmartDoorLockImpl implements SmartDoorLock {

    private static final int MAX_ATTEMPTS = 3;
    private static final int PIN_NOT_SET = -1;
    private static final int PIN_LENGTH = 4;

    private boolean isLocked = false;
    private int pin = PIN_NOT_SET;
    private int failedAttempts = 0;

    @Override
    public void setPin(int pin) {
        if (this.isLocked || this.isBlocked()) {
            throw new IllegalStateException("Pin cannot be set when the lock is locked or blocked.");
        } else if (pin < 0) {
            throw new IllegalArgumentException("The pin cannot be a negative number.");
        } else if (String.valueOf(pin).length() != PIN_LENGTH) {
            throw new IllegalArgumentException("The pin must be 4 digits long.");
        }
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (!this.isBlocked() && this.isLocked()) {
            this.isLocked = this.pin != pin;
            if (this.isLocked) {
                this.failedAttempts = this.failedAttempts + 1;
            }
        }
    }

    @Override
    public void lock() {
        if (this.pin == PIN_NOT_SET) {
            throw new IllegalStateException("You must set the pin before locking the door.");
        }
        this.isLocked = true;
    }

    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isBlocked() {
        return this.failedAttempts >= MAX_ATTEMPTS;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return this.failedAttempts;
    }

    @Override
    public void reset() {
        this.isLocked = false;
        this.pin = PIN_NOT_SET;
        this.failedAttempts = 0;
    }

}
