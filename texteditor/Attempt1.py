import time
import sys
from PyQt5.QtWidgets import (QApplication, QWidget, 
    QToolTip, QPushButton, QAction, QMainWindow, QColorDialog, QFileDialog,
     QTextEdit, QFontDialog, QProgressBar, QCalendarWidget, QLabel,
     qApp, QTabWidget, QStatusBar, QLCDNumber, QApplication, QMdiArea,
    QMdiSubWindow, QMessageBox, QInputDialog)

from PyQt5.QtGui import QFont, QTextDocument, QIcon, QPixmap
from PyQt5.QtCore import QCoreApplication, QFile, QTextCodec, Qt, QTime, QTimer, pyqtSlot,\
    QSize
from PyQt5.Qt import QDialog, QPrintDialog, QVBoxLayout, QMenu
from fileinput import filename

import find
import wordCount


'''
What I need to work on
Being able to save a document
Work on a find and replace function
Getting Icons
Work on a word count
Work on a bold function to whole text and single word
Work on an alignment function
Work on a highlight function

Work on pacaking this program as an .exe

optimize the code
'''

#For making tabs, holds the text widget
class MyTableWidget(QWidget):
    
    def __init__(self, parent):
        super(QWidget, self).__init__(parent)
        self.layout = QVBoxLayout(self)
        
        
        #Makes sure there is a close button fo the window
        self.mdi = QMdiArea()
        self.tabs = QTabWidget()
        self.tabs.setTabsClosable(True)
        self.tabs.tabCloseRequested.connect(self.closeTab)
                
        self.tabs.resize(300, 300)
        
        self.layout.addWidget(self.tabs)
        self.setLayout(self.layout)
        
    
    def add(self, namefile):
        
    
        #this adds a textfile to a tab widget
        self.tab = QWidget()
        self.tabs.addTab(self.tab, namefile)
        self.tab.layout = QVBoxLayout(self)
        
        
        
        self.textEdit = QTextEdit(self)
        
        
        
        self.textEdit.setUndoRedoEnabled(True)
        self.textEdit.setGeometry(20, 20, 200, 200)
        self.tab.layout.addWidget(self.textEdit)
        
         
        self.tab.setLayout(self.tab.layout)
        
        
        

    #closes the current tab when the close button is clicked
    def closeTab(self):
        self.tabs.removeTab(self.tabs.currentIndex())
        
        
    
    
    
    
class DigitalClock(QWidget):
    def __init__(self):
        super().__init__()
        self.left = 10
        self.top = 10
        self.width = 320
        self.height = 200
        self.initUI()
 
    def initUI(self):
        self.setWindowTitle(self.title)
        self.setGeometry(self.left, self.top, self.width, self.height)
        
        localtime = time.asctime( time.localtime(time.time()) )

 
        #QMessageBox.question(self, 'Clock', "Clock", QMessageBox.Reset, QMessageBox.Reset)
    
        
        buttonReply = QMessageBox.question(self, 'PyQt5 message', "Do you like PyQt5?", QMessageBox.Yes | QMessageBox.No, QMessageBox.No)
        if buttonReply == QMessageBox.Yes:
            print('Yes clicked.')
        else:
            print('No clicked.')
 
    
        self.show()

class Elite(QMainWindow):
    
    
    def __init__(self):
        super().__init__()
        
        self.setStatusBar(QStatusBar())
        #self.mdi = QMdiArea()
        
        self.initUI()
    
        
    def initUI(self):
        QToolTip.setFont(QFont('SansSerif', 10))
        
        self.setToolTip('This is a <b>QWidget</b> widget')
        
        
        self.setGeometry(200, 200, 400, 400)
        self.setWindowTitle('Tooltips')

        
        self.initUI2()
        self.menu()
        self.recent()
        
    def initUI2(self):
        #For when a file is chosen from the 
        
        '''
        self.textEdit = QTextEdit(self)
        self.textEdit.setGeometry(100, 200, 200, 200)
        self.setCentralWidget(self.textEdit)
        
        self.textEdit.setGeometry(100, 19, 250, 150)
        
        '''
        self.tab_widget = MyTableWidget(self)
        self.setCentralWidget(self.tab_widget)
        
        
        self.cal()    
        self.time()
        
        self.open()
        self.save()
        self.fontdialog()
        self.color()
        self.align()
        self.close()
        
        self.new_file()
        self.show()   
        
    def menu(self):
        
        exitAction = QAction('&Exit', self)        
        exitAction.setShortcut('Ctrl+Shift+Q')
        exitAction.setStatusTip('Exit application')
        exitAction.triggered.connect(qApp.quit)
        
        
        #shows the recent files 
        '''
        self.recentAction = QAction('&Recent', self)        
        self.recentAction.setShortcut('Ctrl+R')
        self.recentAction.setStatusTip('Get recent files')
        self.recentAction.triggered.connect(self.recent)
        '''
                
        #This one works, except I cannot print anything
        printAction = QAction('&Print', self)        
        printAction.setShortcut('Ctrl+Shift+R')
        printAction.setStatusTip('Print')
        printAction.triggered.connect(self.printpage)
        
        
        mainMenu = self.menuBar() 
        fileMenu = mainMenu.addMenu('File')
        fileMenu.addAction(exitAction)
        #fileMenu.addAction(self.recentAction)
        fileMenu.addAction(printAction)
        
        
        self.recentMenu = fileMenu.addMenu('Recent Files')
        
        
        
        #need to create the function of 
        copyAction = QAction('&Copy', self)        
        copyAction.setShortcut('Ctrl+C')
        copyAction.setStatusTip('Copy')
        copyAction.triggered.connect(self.printpage)
        
        
        pasteAction = QAction('&Paste', self)        
        pasteAction.setShortcut('Ctrl+P')
        pasteAction.setStatusTip('Paste')
        pasteAction.triggered.connect(self.printpage)
        
        
        #Need to make a search and replace dialog
        findAction = QAction('&find', self)        
        findAction.setShortcut('Ctrl+F')
        findAction.setStatusTip('find')
        findAction.triggered.connect(find.Find(self.tab_widget).show) 
        #findAction.triggered.connect(self.findword) 
        
        
        wordcountAction = QAction('&wordCount', self)        
        wordcountAction.setShortcut('Ctrl+W')
        wordcountAction.setStatusTip('find')
        wordcountAction.triggered.connect(self.wordcount)   
        
        editMenu = mainMenu.addMenu('Edit')
        editMenu.addAction(copyAction)
        editMenu.addAction(pasteAction)
        editMenu.addAction(findAction)
        editMenu.addAction(wordcountAction)
        
        
        #This is for changing any of the colors of the text editors
        
    
        
        colorBackgroundAction = QAction('&ColorBackground', self)        
        colorBackgroundAction.setShortcut('Ctrl+Shift+C')
        colorBackgroundAction.setStatusTip('MainWindow Background')
        colorBackgroundAction.triggered.connect(self.color_picker_main_background)
        
        
    
        colorTextAction = QAction('&TextColor', self)        
        colorTextAction.setShortcut('Ctrl+1')
        colorTextAction.setStatusTip('MainWindow text color')
        colorTextAction.triggered.connect(self.color_picker_main_text)
        
        tabTextColorAction = QAction('&TabBackground', self)        
        tabTextColorAction.setShortcut('Ctrl+2')
        tabTextColorAction.setStatusTip('tab text color')
        tabTextColorAction.triggered.connect(self.color_picker)
        
        tabBackgroundColorAction = QAction('&TabTextColor', self)        
        tabBackgroundColorAction.setShortcut('Ctrl+3')
        tabBackgroundColorAction.setStatusTip('tab Background')
        tabBackgroundColorAction.triggered.connect(self.color_picker_tab_background)
        
        
        colorMenu = editMenu.addMenu('Color')
        colorMenu.addAction(colorBackgroundAction)
        colorMenu.addAction(colorTextAction)
        colorMenu.addAction(tabTextColorAction)
        colorMenu.addAction(tabBackgroundColorAction)
        
        
        
        #editMenu.addAction()
        
        
        
        
        self.menu2(mainMenu)

        
        
    #Working on making the     
    def menu2(self, mainMenu):
        toolsMenu = mainMenu.addMenu('Tools')
        
        
        manualAction = QAction('&Manual', self)        
        manualAction.setShortcut('Ctrl+Shift+M')
        manualAction.setStatusTip('Manual')
        manualAction.triggered.connect(self.printpage)
        
        
        helpMenu = mainMenu.addMenu('Help')
        helpMenu.addAction(manualAction)
        
        
    def recent(self):
        with open('recentfile.txt', 'r') as f:
            line = f.readline()
            
            counter = 0
            while line:
                counter += 1 
                
                #The max amount of files that can be shown at once
                if counter <= 21:
                    fileAction = QAction(line, self)        
                    fileAction.triggered.connect(lambda: self.open_picker(line))
                
                    self.recentMenu.addAction(fileAction)
                
                    line = f.readline()
                else:
                    break
        
            
    
    #Widgets
    
    #finds the words the users specifies
    
    
    
    def findword(self):
        
        
        text, ok = QInputDialog.getText(self, "find Word", "Enter Word")
        
        
        flag = QTextDocument.FindBackward
        
        if ok:
            self.tab_widget.textEdit.find(text, flag)
        
    
    def time_window(self):
        #This opens a window that shows a time window
        
        
        #clock = DigitalClock(self)
        
        #clock.exec_()
        localtime = time.asctime( time.localtime(time.time()) )

        msg = QMessageBox()
        msg.setIcon(QMessageBox.Information)
        msg.setWindowTitle("Date and Time")
        
        msg.setText(localtime)
        msg.exec_()
        

    
    def time(self):
        localtime = time.asctime( time.localtime(time.time()) )
    
        
        timeaction = QAction(QIcon(QPixmap(":icons/clock.png")), 'Time', self)
        timeaction.setStatusTip(localtime)
        timeaction.setShortcut("Ctrl+T+1")
        timeaction.triggered.connect(self.time_window)
        
        self.toolbar = self.addToolBar('Clock')
        self.toolbar.addAction(timeaction)
    
    #uses the print dialog
    #prints the current page
    def printpage(self):
        printdialog = QPrintDialog(self)
        
        if printdialog.exec_() == QDialog.Accepted:
            self.tab_widget.textEdit.document().print_(printdialog.printer())
    
        
        
    #method for the color picker
    def color_picker(self):
        color = QColorDialog.getColor()
        self.tab_widget.setStyleSheet("color: %s" % color.name())
        
        
    def color_picker_main_background(self):
        color = QColorDialog.getColor()
        self.setStyleSheet("background-color: %s" % color.name())
        
    def color_picker_main_text(self):
        color = QColorDialog.getColor()
        self.setStyleSheet("color: %s" % color.name())
        
        
    def color_picker_tab_background(self):
        color = QColorDialog.getColor()
        self.tab_widget.setStyleSheet("background-color: %s" % color.name())   
    
    #for changing the color of the background
    def color(self):   
        
        coloraction = QAction(QIcon(QPixmap(":icons/color.png")), 'Color', self)
        self.setStatusTip("Choose color")
        coloraction.triggered.connect(self.color_picker)
        
        self.toolbar = self.addToolBar('Color Selector')
        self.toolbar.addAction(coloraction)
        
    
    #supposed to make it easier to open files
    def load(self, f):
        if not QFile.exists(f):
            return False
  
        fh = QFile(f)
        if not fh.open(QFile.ReadOnly):
            return False
  
        data = fh.readAll()
        codec = QTextCodec.codecForHtml(data)
        unistr = codec.toUnicode(data)
  
        if Qt.mightBeRichText(unistr):
            self.tab_widget.textEdit.setHtml(unistr)
        else:
            self.tab_widget.textEdit.setPlainText(unistr)
  
        self.setCurrentFileName(f)
        return True
        
        
        
    #I want to be able to open more types of files now besides text files
    def open_picker(self, file=None):
        
        #need to work on this, it never stays alive
        
        if file == None:
            filename = QFileDialog.getOpenFileName(self, 'Open file', '/home')
        else:
            filename = QFileDialog.getOpenFileName(self, 'Open file', "/" + str(file))
        

        
        
        truefilename = filename[0]
        
        #This part gets the correct name of the file 
        notfound = True
        i = len(truefilename) - 1
        while(notfound):
            if(truefilename[i] == '/'):
                self.truefilename2 = truefilename[i + 1 : len(truefilename)]
                notfound = False
            i-= 1
            
        self.tab_widget.add(self.truefilename2)
        
        self.recentfile(self.truefilename2)
        
        #print(filename)
        
        if filename[0]:
            f = open(filename[0], 'r')
            
            with f:
                
                data = f.read()
                self.tab_widget.textEdit.setText(data)
                
        
        
    #for opening a file
    def open(self):
        
        #openFile = QAction(QIcon(":/color.png"), 'Open', self)
        openFile = QAction(QIcon(QPixmap(":icons/open.png")), 'Open', self)
        openFile.setShortcut('Ctrl+O')
        openFile.setStatusTip('Open new File')
        #openFile.triggered.connect(self.open_picker)
        openFile.triggered.connect(self.open_picker)

        self.toolbar = self.addToolBar('Open File dialog')
        self.toolbar.addAction(openFile)
        
        
    def save_picker(self):
        
        #We store the contents of the text file
        name = QFileDialog.getSaveFileName(self, 'Open file')
        
        file = open(name[0], 'w')
        text = self.tab_widget.textEdit.toPlainText()
        
        file.write(text)
        file.close()
        
        
    #for saving a file 
    #It still does not edit the file that has been opedning
    #Need to fix because it closes after the dialog
    def save(self):
        saveFile = QAction(QIcon(QPixmap(":icons/save.png")), 'Save', self)
        saveFile.setShortcut('Ctrl+S')
        saveFile.setStatusTip('Save file')
        saveFile.triggered.connect(self.save_picker)
        
        self.toolbar = self.addToolBar('Save File')
        self.toolbar.addAction(saveFile)
    
    #can set the font of the window 
    #I want it to only change the font of the text inside the tab windows   
    def font(self):
        font, ok = QFontDialog.getFont()
        
        if ok:
            self.tab_widget.setFont(font)
            
    #for setting the font
    def fontdialog(self):
        wfont = QAction(QIcon(QPixmap(":icons/font.png")), 'Font', self)
        wfont.setShortcut('Ctrl+Shift+f')
        wfont.setStatusTip('Font changer')
        wfont.triggered.connect(self.font)
        
        self.toolbar = self.addToolBar('Font changer')
        self.toolbar.addAction(wfont)
    
    #For setting the calendar date
    def cal(self):
        calender = QAction(QIcon(QPixmap(":icons/calendar.png")), "Calender", self)
        calender.setShortcut('Ctrl+Shift+c')
        calender.setStatusTip("Date Changer")
        calender.triggered.connect(self.calendar)
        
        self.toolbar = self.addToolBar('Date Changer')
        self.toolbar.addAction(calender)

    def calendar(self):
        d = QDialog()
        cal = QCalendarWidget(d)
        cal.setGridVisible(True)
        #d.setWindowModality(ApplicationModal)
        d.setMinimumSize(310, 180)
        d.setMaximumSize(310, 180)
        d.exec_()
        
    def emptytab(self):
        self.tab_widget.add("Untitled.txt")
            
    
    #This creates a blank text file
    def new_file(self):
        newfileaction = QAction(QIcon(QPixmap(":icons/new file.png")), "New", self)
        newfileaction.setShortcut('Ctrl+N')
        newfileaction.setStatusTip("New File")
        newfileaction.triggered.connect(self.emptytab)
        
        self.toolbar = self.addToolBar("New File")
        self.toolbar.addAction(newfileaction)
    
        
    '''
        Let's say for the feature of showing the most recent 
        I would need to save the file names that the text editor last opened or created to a textfile
        Right next to each name I would need to show the where the location of the file,
        I would also want the ability to recall that file
        
        But this is only possible once I figure out how to write to a text file
    '''
    
    #For the alignment of the text in QTextEdit    
    def left(self):
        self.tab_widget.textEdit.setAlignment(Qt.AlignLeft)
        
    def right(self):
        self.tab_widget.textEdit.setAlignment(Qt.AlignRight)

    def justify(self):
        self.tab_widget.textEdit.setAlignment(Qt.AlignJustify)
        
    def align_center(self):
        self.tab_widget.textEdit.setAlignment(Qt.AlignCenter)


    #Creates all of the align text buttons
    def align(self):
        alignleftaction = QAction(QIcon(QPixmap(":icons/left align.png")), "Align Left", self)
        alignleftaction.setStatusTip("Align Text Left")
        alignleftaction.triggered.connect(self.left)
        
        self.toolbar = self.addToolBar("Align Left")
        self.toolbar.addAction(alignleftaction)
        
        alignrightaction = QAction(QIcon(QPixmap(":icons/right align.png")), "Align Right", self)
        alignrightaction.setStatusTip("Align Text Right")
        alignrightaction.triggered.connect(self.right)
        
        self.toolbar = self.addToolBar("Align Text Right")
        self.toolbar.addAction(alignrightaction)
        
        alignjustifyaction = QAction(QIcon(QPixmap(":icons/justify.png")), "Align Justify", self)
        alignjustifyaction.setStatusTip("Align Text Justify")
        alignjustifyaction.triggered.connect(self.justify)
        
        self.toolbar = self.addToolBar("Align Justify")
        self.toolbar.addAction(alignjustifyaction)
        
        aligncenteraction = QAction(QIcon(QPixmap(":icons/center.png")), "Align Center", self)
        aligncenteraction.setStatusTip("Align Text Center")
        aligncenteraction.triggered.connect(self.align_center)
        
        self.toolbar = self.addToolBar("Align Center")
        self.toolbar.addAction(aligncenteraction)
        
    #This shows the most recent files
    def recentfile(self, filename):    
        '''
        when the open method is used it writes the name of the file
        to the most recent file text
        Whent the save file method is used it writes the name of the file to the most
        recent
        
        
        This should read the most recent text files and display a list of 
        
        
        Maybe I might make sure the text file has only say like 30 files and rest are deleted
        everytime this method is run
        '''
        newline = "\n"
        
        with open('recentfile.txt', 'a') as f:
            f.write(filename+newline)
            
            
    def wordcount(self):
        
        wc = wordCount.WordCount(self.tab_widget)
        wc.getText()
        wc.show()        
        

    

def main():
    app = QApplication(sys.argv)
    eliteobj = Elite()
    sys.exit(app.exec_())
   

if __name__ == '__main__':
    main()
