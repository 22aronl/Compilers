.data
	varx: .float 1
.text
.globl main
main:
	la $t0, varx
	lw $a0, ($t0)
	li $v0, 1
	syscall
	