.txt
.globl main
main:
	li $t2, 4
	lw $t1, -100($t2)
	move $a0, $t1
	li $v0, 1
	syscall
	
	li $v0, 10
	syscall
