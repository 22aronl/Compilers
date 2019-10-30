.data 
	message: .asciiz "Hello World"
.text 
.globl main

main:
	la $a0, message
	li $v0, 4
	syscall 
	
	li $v0, 10
	syscall