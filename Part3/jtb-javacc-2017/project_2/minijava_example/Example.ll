
@.Example_vtable = global [0 x i8*] []
@.Fac_vtable = global [2 x i8*] [i8* bitcast (i32 (i8*,i32)* @Fac.ComputeFac to i8*), i8* bitcast (i32 (i8*)* @Fac.testfun to i8*)]
@.B_vtable = global [3 x i8*] [i8* bitcast (i32 (i8*,i32)* @B.ComputeFac to i8*),i8* bitcast (i32 (i8*)* @Fac.testfun to i8*), i8* bitcast (i1 (i8*)* @B.foobar to i8*)]

declare i8* @calloc(i32, i32)
declare i32 @printf(i8*, ...)
declare void @exit(i32)

@_cint = constant [4 x i8] c"%d\0a\00"
@_cOOB = constant [15 x i8] c"Out of bounds\0a\00"
define void @print_int(i32 %i) {
    %_str = bitcast [4 x i8]* @_cint to i8*
    call i32 (i8*, ...) @printf(i8* %_str, i32 %i)
    ret void
}

define void @throw_oob() {
    %_str = bitcast [15 x i8]* @_cOOB to i8*
    call i32 (i8*, ...) @printf(i8* %_str)
    call void @exit(i32 1)
    ret void
}
define i32 @main(){
	ret i32 0
}
define i32 @Fac.ComputeFac(i8 %this, i32 %.num)
	%num = alloca i32
	store i32 %.num, i32* %num
	%num_aux = alloca i32
define i32 @Fac.testfun(i8 %this)
define i1 @B.foobar(i8 %this)
define i32 @B.ComputeFac(i8 %this, i32 %.num)
	%num = alloca i32
	store i32 %.num, i32* %num
