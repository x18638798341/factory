package com.xch.delegate;

public class Boss {

    public static void main(String[] args) {
        Leader leader = new Leader();
        Boss boss = new Boss();
        boss.command("架构", leader);
    }

    public void command(String command, Leader leader) {
        leader.doing(command);
    }
}
