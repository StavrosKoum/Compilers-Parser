
@.ManyClasses_vtable = global [0 x i8*] []
@.A_vtable = global [1 x i8*] [i8* bitcast (i32 (i8*)* @A.get to i8*)]
@.B_vtable = global [2 x i8*] [i8* bitcast (i32 (i8*)* @A.get to i8*), i8* bitcast (i1 (i8*)* @B.set to i8*)]
@.C_vtable = global [2 x i8*] [i8* bitcast (i1 (i8*)* @B.set to i8*), i8* bitcast (i1 (i8*)* @C.reset to i8*)]

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
define i32 @main(){	%rv = alloca i1
	%a = alloca i8*
	%b = alloca i8*
	%c = alloca i8*

	ret i32 0
}
	%data = alloca i1
define i32 @A.get(i8 %this)
	%rv = alloca i32
define i1 @B.set(i8 %this)
	%old = alloca i1
define i1 @C.reset(i8 %this)
	%old = alloca i1
