'''
Created on Jan 9, 2017

@author: jacob

This module creates a dummy interface, a lot of the contents from this would be used
'''
import tkinter
from tkinter import ttk
from tkinter import *
from tkinter import colorchooser
from tkinter import filedialog
'''
from .Tab import TabWindow
from .Topbar import TopbarWindow
from .Menu2 import MenuWindow
'''

class TabWindow(ttk.Notebook):
    '''
    classdocs
    '''


    def __init__(self, master, ntbook):
        '''
        Constructor
        Need to look at whether I should make this a panedwindow class instead
        of a tkinter.Frame class
        
        '''
        #tkinter.Frame.__init__(self, *args, **options)
        
        self.master = master
        self.notebook1 = ntbook
        #thinking of adding a frame
    def create_tab(self, user_text):
        tempframe = ttk.Frame(self.notebook1).grid(row = 1, column = 4)
        self.notebook1.add(tempframe, text = user_text)
        return tempframe
class TopbarWindow(object):
    '''
    the top bar menu
    this does not go anywhere it is always on the 
    '''


    def __init__(self, master, parent):
        '''
        Constructor
        '''
        self.master = master
        self.parent = parent
        
        
        
        self.create_menu()
        
    def create_menu(self):
        self.master.option_add('*tearOff', False)#tells tk not to build a terrible menu
        menubar = Menu(self.master)
        self.master.config(menu = menubar)
        file = Menu(menubar)
        edit = Menu(menubar)
        options = Menu(menubar)
        help_ = Menu(menubar) #need to add underscore to prevent naming conflicts with 
        window = Menu(menubar)
        
        #adding the submenues to the main menu
        menubar.add_cascade(menu = file, label = "File")
        menubar.add_cascade(menu = edit, label = "Edit")
        menubar.add_cascade(menu = options, label = "Options")
        menubar.add_cascade(menu = help_, label = "Help")
        menubar.add_cascade(menu = window, label = "Window")
        
        window.add_command(label = 'New Window', command = self.New)
        
        #creating the submenu of the file tab
        file.add_command(label = 'New    Control-s', command =  self.New)
        file.add_command(label = 'Open   Control-o', command = self.File)
        file.add_command(label = 'Save   Control-n', command = self.Save)
        
        #binding keyevents to window functions
        #the e for lamabda to prevent an exception
        self.master.bind('<Control-s>', lambda e: self.Save)
        self.master.bind('<Control-o>', lambda e: self.File)
        self.master.bind('<Control-n>', lambda e: self.New)
        
        
        
        
        
        help_.add_command(label = 'Manual', command = lambda: print('Opening Manual'))
        help_.add_command(label = 'Support', command = lambda: print('Starting chat with tech support'))
        help_.add_command(label = 'Information', command = lambda: print('Software info'))
        
        settings = Menu(options)
        options.add_cascade(menu = settings, label = "Settings")
        settings.add_command(label = 'Signin', command = lambda: print('Signing in'))#should change that later to a button where you can signin
        settings.add_command(label = 'Background', command = lambda: print('Change background'))
        settings.add_command(label = 'Update', command = lambda: print('Updating..'))
    #need to make this work     
        settings.add_command(label = "Change Settings", command = self.settings)
    #shows the setting bar for chaning things    
    #need to work on making this happen to make these checkbuttons appear 
    #when they are pressed
    def settings(self):
        #settframe = self.parent.create_tab("Settings")
        
        check = ttk.Checkbutton(self.parent, text = "Setting1").grid(row = 1, column = 4)
        check2 = ttk.Checkbutton(self.parent, text = "Setting2").grid(row = 2, column = 4)
        check3 = ttk.Checkbutton(self.parent, text = "Setting3").grid(row = 3, column = 4)
        
        check4 = ttk.Checkbutton(self.parent, text = "Setting4").grid(row = 1, column = 5)
        check5 = ttk.Checkbutton(self.parent, text = "Setting5").grid(row = 2, column = 5)
        check6 = ttk.Checkbutton(self.parent, text = "Setting6").grid(row = 3, column = 5)
        
        
        destroy = ttk.Button(self.parent, text = "Close", command = self.close).grid(row = 10, column = 10)
        
    def close(self):
        self.parent.grid_forget()
        
    #opens a filedialog for asking for a file   
    def File(self):
        return filedialog.askopenfilename()
    #opens a filedialog for saving a file
    def Save(self):
        return filedialog.asksaveasfilename()
    
    def New(self):
        main()
    
        
class MenuWindow(tkinter.Frame):          
    '''
    Creates a side menu
    '''
    def __init__(self, master, text ="", *args, **options):
        self.master = master
        
        tkinter.Frame.__init__(self, *args, **options)

        self.show = tkinter.IntVar()
        self.show.set(0)
        
        self.title_frame = ttk.Frame(self)
        self.title_frame.grid(row = 1, column = 0, sticky = W+E)
        self.title_frame.grid_columnconfigure(0, weight = 2)
        ttk.Label(self.title_frame, text = text).grid(row = 0, column = 0, sticky = W+E, columnspan = 2)
        
        self.toggle_button = ttk.Checkbutton(self.title_frame, width=2, text='+', command=self.toggle,
                                            variable=self.show, style='Toolbutton')
        self.toggle_button.grid(row = 0, column = 3)

        self.sub_frame = tkinter.Frame(self, relief="sunken", borderwidth=1)
        
    def toggle(self):
        if bool(self.show.get()):
            self.sub_frame.grid(sticky = W+E)
            self.toggle_button.configure(text='-')
        else:
            self.sub_frame.grid_forget()
            self.toggle_button.configure(text='+')  
    
        
    def create_menu(self):
        menubar = Menubutton(self.master, text = "Menu1")
        menubar.grid(row = 0, column = 0)
        menubar.menu = Menu(menubar, tearoff = 0)
        menubar["menu"] = menubar.menu
        
        oneVar = StringVar()
        twoVar = StringVar()
        
        menubar.menu.add_checkbutton(label = "one", variable = oneVar)
        menubar.menu.add_checkbutton(label = "two", variable = twoVar)
        
        
    #the result of hitting a link in the menu    
    #needs to know the tab class object being used
    def action(self):
        return ""
        
    #way to make this frame dope    
    def custom(self, choice):
        return ""
 

class Demorun():
    
    def __init__(self):
        self.root = Tk()
        self.frame1 = ttk.Frame(self.root).grid(row = 0, column = 0, stick = W+E, columnspan = 10)
        self.frame2 = ttk.Frame(self.root, relief = "groove", borderwidth = 3).grid(row = 1, column = 0, sticky = W+E)
        self.frame3 = ttk.Frame(self.root).grid(row = 1, column = 1, stick = W+E)
        self.pane1 = PanedWindow(self.root, orient = HORIZONTAL).grid(row  = 2, column = 0, columnspan = 10)
        
        self.notebook1 = ttk.Notebook(self.root)
        
        #self.pane1 = PanedWindow(self.root, orient = HORIZONTAL).grid(row = 0, column = 0)
        #self.pane2 = PanedWindow(self.root, orient = VERTICAL).grid(row = 1, column = 0)
        #self.pane3 = PanedWindow(self.root, orient = HORIZONTAL).grid(row = 1, column = 1)
        
        self.makebetter()
        
        self.root.minsize(400, 400)
        #self.root.configure(background = 'black')
    def change_color(self):
        #allows for the program
        color = colorchooser.askcolor()
        color_name = color[1]
        self.root.configure(background = color_name)
    
    #used to make most of the big objects from other classes
    def makebetter(self):  
        tabwin = TabWindow(self.root, self.frame2)
        topbarwin = TopbarWindow(self.root, self.frame3)

        
        self.add_scroll()
        menu1 = MenuWindow(self.frame2, text = "Menu1     ", relief ="raised", borderwidth = 1)
        menu1.grid(row = 1, column = 0, pady=5, padx=5, sticky = W+E, columnspan = 4)
        
        
        #need to add the command attribute later
        #buttons are going to go into on of the frames
        button = ttk.Button(menu1.sub_frame, text = "Button").grid(row = 0, column = 0)
        button1 = ttk.Button(menu1.sub_frame, text = "Button1").grid(row = 1, column = 0)
        button2 = ttk.Button(menu1.sub_frame, text = "Button2").grid(row = 2, column = 0)
        button3 = ttk.Button(menu1.sub_frame, text = "Button3").grid(row = 3, column = 0)
        button4 = ttk.Button(menu1.sub_frame, text = "Button4").grid(row = 4, column = 0)
         
        menu2 = MenuWindow(self.frame2, text = "Menu2     ", relief ="raised", borderwidth = 1)
        menu2.grid(row = 2, column = 0, pady=5, padx=5, sticky = W+E, columnspan = 4)
        
        button5 = ttk.Button(menu2.sub_frame, text = "Button5").grid(row = 0, column = 0)
        button6 = ttk.Button(menu2.sub_frame, text = "Button6").grid(row = 1, column = 0)
        button7 = ttk.Button(menu2.sub_frame, text = "Button7").grid(row = 2, column = 0)
        button8 = ttk.Button(menu2.sub_frame, text = "Button8").grid(row = 3, column = 0)
        button9 = ttk.Button(menu2.sub_frame, text = "Button9").grid(row = 4, column = 0)
        
        menu3 = MenuWindow(self.frame2, text = "Menu3     ", relief ="raised", borderwidth = 1)
        menu3.grid(row = 3, column = 0, pady=5, padx=5, sticky = W+E, columnspan = 4)
        
        button10 = ttk.Button(menu3.sub_frame, text = "Button10").grid(row = 0, column = 0)
        button11 = ttk.Button(menu3.sub_frame, text = "Button11").grid(row = 1, column = 0)
        button12 = ttk.Button(menu3.sub_frame, text = "Button12").grid(row = 2, column = 0)
        button13 = ttk.Button(menu3.sub_frame, text = "Button13").grid(row = 3, column = 0)
        button14 = ttk.Button(menu3.sub_frame, text = "Button14").grid(row = 4, column = 0)
        
        menu4 = MenuWindow(self.frame2, text = "Menu3     ", relief ="raised", borderwidth = 1)
        menu4.grid(row = 4, column = 0, pady=5, padx=5, sticky = W+E, columnspan = 4)
        
        button15 = ttk.Button(menu4.sub_frame, text = "Button15").grid(row = 0, column = 0)
        button16 = ttk.Button(menu4.sub_frame, text = "Button16").grid(row = 1, column = 0)
        button17 = ttk.Button(menu4.sub_frame, text = "Button17").grid(row = 2, column = 0)
        button18 = ttk.Button(menu4.sub_frame, text = "Button18").grid(row = 3, column = 0)
        button19 = ttk.Button(menu4.sub_frame, text = "Button19").grid(row = 4, column = 0)
        
        menu5 = MenuWindow(self.frame2, text = "Menu4     ", relief ="raised", borderwidth = 1)
        menu5.grid(row = 5, column = 0, pady=5, padx=5, sticky = W+E, columnspan = 4)
        
        button20 = ttk.Button(menu5.sub_frame, text = "Button20").grid(row = 0, column = 0)
        button21 = ttk.Button(menu5.sub_frame, text = "Button21").grid(row = 1, column = 0)
        button22 = ttk.Button(menu5.sub_frame, text = "Button22").grid(row = 2, column = 0)
        button23 = ttk.Button(menu5.sub_frame, text = "Button23").grid(row = 3, column = 0)
        button24 = ttk.Button(menu5.sub_frame, text = "Button24").grid(row = 4, column = 0)
        
        
        colorbutton = ttk.Button(self.frame1, text = "color", command = self.change_color).grid(row = 0, column = 9)
        singupbutton = ttk.Button(self.frame1, text = "Login").grid(row =  0, column = 10)
        
        but4 = ttk.Button(self.frame1, text = "but4").grid(row = 0, column = 4)
        but = ttk.Button(self.frame1, text = "but").grid(row = 0, column = 5)
        but1 = ttk.Button(self.frame1, text = "but1").grid(row = 0, column = 6)
        but2 = ttk.Button(self.frame1, text = "but2").grid(row = 0, column = 7)
        but3 = ttk.Button(self.frame1, text = "but3").grid(row = 0, column = 8)
    
    #should a scrollbar to a widget
    #need to try to make it possible to  add a scrollbar to a frame
    def add_scroll(self):
        scrollbar = ttk.Scrollbar(self.frame2, orient = VERTICAL )    
        
        
def main():
    Demorun()
    
    
if __name__ =="__main__":
    main()  

    