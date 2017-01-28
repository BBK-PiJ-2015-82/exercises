class BankAccount {
  private float balance;

  BankAccount() {
    balance = 100;
  }

  public void deposit(float amount) {
    this.balance += amount;
  }

  public float showBalance() {
    return this.balance;
  }
}
