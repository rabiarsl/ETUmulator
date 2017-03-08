grammar Arm;

prog
    : (line? EOL)+
    ;

line
    : instruction
    | label
    ;

instruction
    : add
    | adds
    | adc
    | adcs
    | sub
    | sbc
    | sbcs
    | subs
    | rsb
    | rsbs
    | rsc
    | rscs
    | mul
    | muls
    | mla
    | mlas
    | mls
    | umull
    | umulls
    | umlal
    | umlals
    | smull
    | smulls
    | smlal
    | smlals
    | sdiv
    | udiv
    | mov
    | movs
    | mvn
    | mvns
    | asr
    | asrs
    | lsl
    | lsls
    | lsr
    | lsrs
    | ror
    | rors
    | rrx
    | rrxs
    | cmp
    | cmn
    | tst
    | teq
    | and
    | ands
    | eor
    | eors
    | orr
    | orrs
    | orn
    | orns
    | bic
    | bics
    | b
    | beq
    | bne
    | bcs
    | bhs
    | bcc
    | blo
    | bmi
    | bpl
    | bvs
    | bvc
    | bhi
    | bls
    | bge
    | blt
    | bgt
    | ble
    | bal
    | bl
    | ldr
    | ldrb
    | ldrsb
    | ldrh
    | ldrsh
    | str
    | strb
    | strsb
    | strh
    | strsh
    | push
    | pop
    | nop
    ;

add
    : ADD rd COMMA rn COMMA operand2
    ;

ADD
    : A D D
    ;

adds
    : ADDS rd COMMA rn COMMA operand2
    ;

ADDS
    : A D D S
    ;

adc
    : ADC rd COMMA rn COMMA operand2
    ;

ADC
    : A D C
    ;

adcs
    : ADCS rd COMMA rn COMMA operand2
    ;

ADCS
    : A D C S
    ;

sub
    : SUB rd COMMA rn COMMA operand2
    ;

SUB
    : S U B
    ;

subs
    : SUBS rd COMMA rn COMMA operand2
    ;

SUBS
    : S U B S
    ;

sbc
    : SBC rd COMMA rn COMMA operand2
    ;

SBC
    : S B C
    ;

sbcs
    : SBCS rd COMMA rn COMMA operand2
    ;

SBCS
    : S B C S
    ;

rsb
    : RSB rd COMMA rn COMMA operand2
    ;

RSB
    : R S B
    ;

rsbs
    : RSBS rd COMMA rn COMMA operand2
    ;

RSBS
    : R S B S
    ;

rsc
    : RSC rd COMMA rn COMMA operand2
    ;

RSC
    : R S C
    ;

rscs
    : RSCS rd COMMA rn COMMA operand2
    ;

RSCS
    : R S C S
    ;

mul
    : MUL rd COMMA rm COMMA rs
    ;

MUL
    : M U L
    ;

muls
    : MULS rd COMMA rm COMMA rs
    ;

MULS
    : M U L S
    ;

mla
    : MLA rd COMMA rm COMMA rs COMMA rn
    ;

MLA
    : M L A
    ;

mlas
    : MLAS rd COMMA rm COMMA rs COMMA rn
    ;

MLAS
    : M L A S
    ;

mls
    : MLS rd COMMA rm COMMA rs COMMA rn
    ;

MLS
    : M L S
    ;

umull
    : UMULL rdlo COMMA rdhi COMMA rm COMMA rs
    ;

UMULL
    : U M U L L
    ;

umulls
    : UMULLS rdlo COMMA rdhi COMMA rm COMMA rs
    ;

UMULLS
    : U M U L L S
    ;

umlal
    : UMLAL rdlo COMMA rdhi COMMA rm COMMA rs
    ;

UMLAL
    : U M L A L
    ;

umlals
    : UMLALS rdlo COMMA rdhi COMMA rm COMMA rs
    ;

UMLALS
    : U M L A L S
    ;

smull
    : SMULL rdlo COMMA rdhi COMMA rm COMMA rs
    ;

SMULL
    : S M U L L
    ;

smulls
    : SMULLS rdlo COMMA rdhi COMMA rm COMMA rs
    ;

SMULLS
    : S M U L L S
    ;

smlal
    : SMLAL rdlo COMMA rdhi COMMA rm COMMA rs
    ;

SMLAL
    : S M L A L
    ;

smlals
    : SMLALS rdlo COMMA rdhi COMMA rm COMMA rs
    ;

SMLALS
    : S M L A L S
    ;

sdiv
    : SDIV rd COMMA rn COMMA rm
    ;

SDIV
    : S D I V
    ;

udiv
    : UDIV rd COMMA rn COMMA rm
    ;

UDIV
    : U D I V
    ;

mov
    : MOV rd COMMA operand2
    ;

MOV
    : M O V
    ;

movs
    : MOVS rd COMMA operand2
    ;

MOVS
    : M O V S
    ;

mvn
    : MVN rd COMMA operand2
    ;

MVN
    : M V N
    ;

mvns
    : MVNS rd COMMA operand2
    ;

MVNS
    : M V N S
    ;
asr
    : ASR rd COMMA rm COMMA (rs | shift
)
    ;

ASR
    : A S R
    ;

asrs
    : ASRS rd COMMA rm COMMA (rs | shift)
    ;

ASRS
    : A S R S
    ;

lsl
    : LSL rd COMMA rm COMMA (rs | shift)
    ;

LSL
    : L S L
    ;

lsls
    : LSLS rd COMMA rm COMMA (rs | shift)
    ;

LSLS
    : L S L S
    ;

lsr
    : LSR rd COMMA rm COMMA (rs | shift)
    ;

LSR
    : L S R
    ;

lsrs
    : LSRS rd COMMA rm COMMA (rs | shift)
    ;

LSRS
    : L S R S
    ;

ror
    : ROR rd COMMA rm COMMA (rs | shift)
    ;

ROR
    : R O R
    ;

rors
    : RORS rd COMMA rm COMMA (rs | shift)
    ;

RORS
    : R O R S
    ;

rrx
    : RRX rd COMMA rm
    ;
RRX
    : R R X
    ;

rrxs
    : RRXS rd COMMA rm
    ;

RRXS
    : R R X S
    ;

cmp
    : CMP rn COMMA operand2
    ;

CMP
    : C M P
    ;

cmn
    : CMN rn COMMA operand2
    ;

CMN
    : C M N
    ;

tst
    : TST rn COMMA operand2
    ;

TST
    : T S T
    ;

teq
    : TEQ rn COMMA operand2
    ;

TEQ
    : T E Q
    ;

and
    : AND rd COMMA rn COMMA operand2
    ;

AND
    : A N D
    ;

ands
    : ANDS rd COMMA rn COMMA operand2
    ;

ANDS
    : A N D S
    ;

eor
    : EOR rd COMMA rn COMMA operand2
    ;

EOR
    : E O R
    ;

eors
    : EORS rd COMMA rn COMMA operand2
    ;

EORS
    : E O R S
    ;

orr
    : ORR rd COMMA rn COMMA operand2
    ;

ORR
    : O R R
    ;

orrs
    : ORRS rd COMMA rn COMMA operand2
    ;

ORRS
    : O R R S
    ;

orn
    : ORN rd COMMA rn COMMA operand2
    ;

ORN
    : O R N
    ;

orns
    : ORNS rd COMMA rn COMMA operand2
    ;

ORNS
    : O R N S
    ;

bic
    : BIC rd COMMA rn COMMA operand2
    ;

BIC
    : B I C
    ;

bics
    : BICS rd COMMA rn COMMA operand2
    ;

BICS
    : B I C S
    ;

b
    : NON_CONDITIONAL_BRANCH LABEL
    ;

NON_CONDITIONAL_BRANCH
    : B
    ;

beq
    : BEQ LABEL
    ;

BEQ
    : B E Q
    ;

bne
    : BNE LABEL
    ;

BNE
    : B N E
    ;

bcs
    : BCS LABEL
    ;

BCS
    : B C S
    ;

bhs
    : BHS LABEL
    ;

BHS
    : B H S
    ;

bcc
    : BCC LABEL
    ;

BCC
    : B C C
    ;

blo
    : BLO LABEL
    ;

BLO
    : B L O
    ;

bmi
    : BMI LABEL
    ;

BMI
    : B M I
    ;

bpl
    : BPL LABEL
    ;

BPL
    : B P L
    ;

bvs
    : BVS LABEL
    ;

BVS
    : B V S
    ;

bvc
    : BVC LABEL
    ;

BVC
    : B V C
    ;

bhi
    : BHI LABEL
    ;

BHI
    : B H I
    ;

bls
    : BLS LABEL
    ;

BLS
    : B L S
    ;

bge
    : BGE LABEL
    ;

BGE
    : B G E
    ;

blt
    : BLT LABEL
    ;

BLT
    : B L T
    ;

bgt
    : BGT LABEL
    ;

BGT
    : B G T
    ;

ble
    : BLE LABEL
    ;

BLE
    : B L E
    ;

bal
    : BAL LABEL
    ;

BAL
    : B A L
    ;

bl
    : BL LABEL
    ;

BL
    : B L
    ;

ldr
    : LDR rd COMMA LBRACK rn (COMMA offset)? RBRACK
    | LDR rd COMMA LBRACK rn RBRACK (COMMA offset)?
    | LDR rd COMMA ASSIGN (LABEL | number)
    ;

LDR
    : L D R
    ;

ldrb
    : LDRB rd COMMA LBRACK rn (COMMA offset)? RBRACK
    | LDRB rd COMMA LBRACK rn RBRACK (COMMA offset)?
    ;

LDRB
    : L D R B
    ;

ldrsb
    : LDRSB rd COMMA LBRACK rn (COMMA offset)? RBRACK
    | LDRSB rd COMMA LBRACK rn RBRACK (COMMA offset)?
    ;

LDRSB
    : L D R S B
    ;

ldrh
    : LDRH rd COMMA LBRACK rn (COMMA offset)? RBRACK
    | LDRH rd COMMA LBRACK rn RBRACK (COMMA offset)?
    ;

LDRH
    : L D R H
    ;

ldrsh
    : LDRSH rd COMMA LBRACK rn (COMMA offset)? RBRACK
    | LDRSH rd COMMA LBRACK rn RBRACK (COMMA offset)?
    ;

LDRSH
    : L D R S H
    ;

str
    : STR rd COMMA LBRACK rn (COMMA offset)? RBRACK
    | STR rd COMMA LBRACK rn RBRACK (COMMA offset)?
    ;

STR
    : S T R
    ;

strb
    : STRB rd COMMA LBRACK rn (COMMA offset)? RBRACK
    | STRB rd COMMA LBRACK rn RBRACK (COMMA offset)?
    ;

STRB
    : S T R B
    ;

strsb
    : STRSB rd COMMA LBRACK rn (COMMA offset)? RBRACK
    | STRSB rd COMMA LBRACK rn RBRACK (COMMA offset)?
    ;

STRSB
    : S T R S B
    ;

strh
    : STRH rd COMMA LBRACK rn (COMMA offset)? RBRACK
    | STRH rd COMMA LBRACK rn RBRACK (COMMA offset)?
    ;

STRH
    : S T R H
    ;

strsh
    : STRSH rd COMMA LBRACK rn (COMMA offset)? RBRACK
    | STRSH rd COMMA LBRACK rn RBRACK (COMMA offset)?
    ;

STRSH
    : S T R S H
    ;

push
    : PUSH LBRACE reglist RBRACE
    ;

PUSH
    : P U S H
    ;

pop
    : POP LBRACE reglist RBRACE
    ;

POP
    : P O P
    ;

nop
    : NOP
    ;

NOP
    : N O P
    ;

rd
    : REGISTER
    ;

rn
    : REGISTER
    ;

rm
    : REGISTER
    ;

rs
    : REGISTER
    ;

rdlo
    : REGISTER
    ;

rdhi
    : REGISTER
    ;

REGISTER
    : R '0'
    | R '1'
    | R '2'
    | R '3'
    | R '4'
    | R '5'
    | R '6'
    | R '7'
    | R '8'
    | R '9'
    | R '10'
    | R '11'
    | R '12'
    | R '13'
    | R '14'
    ;

reglist
    : (REGISTER | LR | PC) (COMMA REGISTER | LR | PC)*
    ;

LR
    : L R
    ;

PC
    : P C
    ;

operand2
    : rm
    | shiftedrm
    | imm8m
    ;

shiftedrm
    : rm COMMA shiftOption rs
    ;

shiftOption
    : LSL
    | LSR
    | ASR
    | ROR
    ;

shift
    : number
    ;

imm8m
    : number
    ;

offset
    : number
    ;

number
    : decimal
    | hex
    ;

decimal
    : DECIMAL
    ;

hex
    : HEX
    ;

DECIMAL
    : DASH? [0-9]+
    ;

HEX
    : DASH? '0x' [0-9a-fA-F]+
    ;

label
    : LABEL COLON
    ;

LABEL
    : [a-zA-Z0-9_]+
    ;

fragment A:('a'|'A');
fragment B:('b'|'B');
fragment C:('c'|'C');
fragment D:('d'|'D');
fragment E:('e'|'E');
fragment F:('f'|'F');
fragment G:('g'|'G');
fragment H:('h'|'H');
fragment I:('i'|'I');
fragment J:('j'|'J');
fragment K:('k'|'K');
fragment L:('l'|'L');
fragment M:('m'|'M');
fragment N:('n'|'N');
fragment O:('o'|'O');
fragment P:('p'|'P');
fragment Q:('q'|'Q');
fragment R:('r'|'R');
fragment S:('s'|'S');
fragment T:('t'|'T');
fragment U:('u'|'U');
fragment V:('v'|'V');
fragment W:('w'|'W');
fragment X:('x'|'X');
fragment Y:('y'|'Y');
fragment Z:('z'|'Z');

ASSIGN
    : '='
    ;

LBRACK
    : '['
    ;

RBRACK
    : ']'
    ;

LBRACE
    : '{'
    ;

RBRACE
    : '}'
    ;

DASH
    : '#'
    ;
COMMA
    : ','
    ;

COLON
    : ':'
    ;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;

WS
    :[ \t\u000C]+ -> skip
    ;

EOL
    : '\r'? '\n'
    ;
