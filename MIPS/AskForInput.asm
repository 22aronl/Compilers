.text
.globl main
main:
	
loop:
	li $v0, 5
	syscall
	beq $v0, 0, after
	addu $t0, $t0, $v0
	j loop
	
after:
	move $a0, $t0
	li $v0, 1
	syscall
	
	li $v0, 10
	syscall
