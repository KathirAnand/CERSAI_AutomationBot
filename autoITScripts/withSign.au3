#Region ;**** Directives created by AutoIt3Wrapper_GUI ****
#AutoIt3Wrapper_Compression=4
#AutoIt3Wrapper_UseX64=y
#EndRegion ;**** Directives created by AutoIt3Wrapper_GUI ****
Sleep(500)
ControlClick("Signing Wizard","","SysListView321")

Sleep(500)
ControlClick("Signing Wizard","","Button1")

Sleep(500)
ControlFocus("Verify User PIN","","Edit1")

Sleep(500)
ControlSetText("Verify User PIN","","Edit1",$CmdLine[1])

Sleep(300)
ControlClick("Verify User PIN","","Button2")