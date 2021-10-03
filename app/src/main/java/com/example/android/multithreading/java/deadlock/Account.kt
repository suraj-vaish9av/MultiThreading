package com.example.android.multithreading.java.deadlock

class Account {
    var balance = 10000

    fun deposit(amount:Int){
        balance+=amount
    }

    fun withdraw(amount: Int){
        balance-=amount
    }

    companion object{
        fun transfer(account1:Account, account2: Account, amount: Int){
            account1.withdraw(amount)
            account2.deposit(amount)
        }
    }
}