package com.coaching.saas.model;
public final class Enums {
  private Enums(){}
  public enum Role { SUPER_ADMIN, COACHING_ADMIN, TEACHER }
  public enum Status { ACTIVE, INACTIVE, TRIAL, EXPIRED, SUSPENDED }
  public enum Plan { FREE_TRIAL, BASIC, PRO, PREMIUM }
  public enum AttendanceStatus { PRESENT, ABSENT, LATE }
  public enum PaymentMode { CASH, UPI, BANK_TRANSFER, CARD, OTHER }
}
