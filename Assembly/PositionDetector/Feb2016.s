#turn ball red when it reaches the edge
#Requirements:
#create LED MATRIX(Height : 20, Width : 20) and DPAD
#set 1ms frequency

.data
# Any memory-based data are held here

ball:
    .byte 255, 255, 255
    .byte 255, 255, 255
    .byte 255, 255, 255
black:
    .byte 0, 0, 0
    .byte 0, 0, 0
    .byte 0, 0, 0
red: 
    .byte 255, 0, 0
    .byte 0, 255, 0
    .byte 0, 0, 255
    

.text
# Code segment
    li a1, LED_MATRIX_0_BASE
    li a2, 20 #LED_MATRIX_0_WIDTH
    li a3, 20 #LED_MATRIX_0_HEIGHT
    mul t1,a3,a2 #width x height
    addi t2,zero,2 #set value of t2 = 2
    mul t1,t1,t2 #t1 x t2 to calculate offset to get to middle row 
    add a1,a1,t1 #add offset  to a1 to get to the middle row
    addi t1,zero,36 #36 = 9 x 4 where 9 is number of columns we need to skip to get to the middle column and 4 the size of every word 
    add a1,a1,t1 #add offset  to a1 to get to the middle column
    jal ra, makeWhite
    
    
    li t3,1 # set value of t3 = 1
    li t4,10 #index of rows
    li t5, 9 #index of column, ball spawns in column 9
    li t6, 19 #index of last column and rows(rows/columns[0-19])
    
    
checkMoves:
    lw t1, D_PAD_0_LEFT #load left dpad button
    beq t3, t1, left #check if left dpad is clicked
    lw t1, D_PAD_0_RIGHT  #load right dpad button
    beq t3, t1, right #check if right dpad is clicked
    lw t1, D_PAD_0_UP #load up dpad button
    beq t3, t1, up #check if up dpad is clicked
    lw t1, D_PAD_0_DOWN #load down dpad button
    beq t3, t1, down #check if down dpad is clicked
    j checkMoves
    
left:
    beq t5, zero, checkMoves #skip move
    jal ra,makeBlack #turn off pixel
    addi a1, a1, -4 #move one box left
    addi t5,t5, -1 #decrease column position counter
    jal ra,chooseColor #turn on pixel
    lw t1, D_PAD_0_LEFT #load left dpad button
    beq t3, t1, waitL #check if left dpad is clicked
    j checkMoves
    
right:
    beq t5, t6, checkMoves #skip move 
    jal ra,makeBlack #turn off pixel
    addi a1, a1, 4 #move one box right
    addi t5, t5, 1 #increase column position counter
    jal ra,chooseColor #turn on pixel
    lw t1, D_PAD_0_RIGHT #load right dpad button 
    beq t3, t1, waitR #check if right dpad is clicked
    j checkMoves
    
up:
    beq t4, zero, checkMoves #skip move
    jal ra, makeBlack
    addi a1, a1, -80 #move one box up, 80 = 20 x 4 where 20 is number of rows and 4 size of each block
    addi t4,t4,-1 #decrease row position counter
    jal ra,chooseColor
    lw t1, D_PAD_0_UP #load up dpad button
    beq t3, t1, waitU #check if up dpad is clicked
    j checkMoves
down:
    beq t4, t6, checkMoves #skip move
    jal ra, makeBlack
    addi a1, a1, 80 #move one box down, 80 = 20 x 4 where 20 is number of rows and 4 size of each block
    addi t4,t4,1
    jal ra, chooseColor
    lw t1, D_PAD_0_DOWN #load down dpad button
    beq t3, t1, waitD #check if down dpad is clicked
    j checkMoves

makeWhite:
    la a0, ball
    lbu t0, 0(a0) #load red
    lbu t1, 1(a0) #load green
    lbu t2, 2(a0) #load blue
    slli t0, t0,   16  # place red at the 3rd byte of "led" word
    slli t1, t1,   8   #   green at the 2nd
    or   t2, t2,   t1  # combine green, blue
    or   t2, t2,   t0  # Add red to the above
    sw   t2, 0(a1)   #light pixel
    jr ra

makeBlack:
    la a0, black
    lbu t0, 0(a0) #load red
    lbu t1, 1(a0) #load green
    lbu t2, 2(a0) #load blue
    slli t0, t0,   16  # place red at the 3rd byte of "led" word
    slli t1, t1,   8   #   green at the 2nd
    or   t2, t2,   t1  # combine green, blue
    or   t2, t2,   t0  # Add red to the above
    sw   t2, 0(a1)   #light pixel
    jr ra

makeRed:
    la a0, red
    lbu t0, 0(a0) #load red
    lbu t1, 1(a0) #load green
    lbu t2, 2(a0) #load blue
    slli t0, t0,   16  # place red at the 3rd byte of "led" word
    slli t1, t1,   8   #   green at the 2nd
    or   t2, t2,   t1  # combine green, blue
    or   t2, t2,   t0  # Add red to the above
    sw   t2, 0(a1)   #light pixel
    jr ra
    
chooseColor:
    beq t5, zero, makeRed #if column[0] make it red
    beq t5, t6, makeRed #else if column[19] make it red
    beq zero, t4, makeRed #else if first row make it red
    beq t6, t4, makeRed #else if last row make it red
    j makeWhite #else make it white
    jr ra
    
waitL:
    lw t1, D_PAD_0_LEFT #load left dpad button
    beq zero, t1, checkMoves #check if left dpad is clicked
    j waitL

waitR:
    lw t1, D_PAD_0_RIGHT #load rightdpad button
    beq zero, t1, checkMoves #check if right dpad is clicked
    j waitR    
    
waitU:
    lw t1, D_PAD_0_UP #load up dpad button
    beq zero, t1, checkMoves #check if up dpad is clicked
    j waitU

waitD:
    lw t1, D_PAD_0_DOWN #load down dpad button
    beq zero, t1, checkMoves #check if down dpad is clicked
    j waitD    



    