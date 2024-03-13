#Region ;**** Directives created by AutoIt3Wrapper_GUI ****
#AutoIt3Wrapper_UseX64=y
#EndRegion ;**** Directives created by AutoIt3Wrapper_GUI ****
Sleep(300)
ControlFocus("Verify User PIN","","Edit1")

Sleep(300)
ControlSetText("Verify User PIN","","Edit1","int12345")

Sleep(300)
ControlClick("Verify User PIN","","Button2")