
;; code for the presentation
(progn
  (defvar hd:*presentation-code-buffer*  (current-buffer))
  (defvar hd:*presentation-previous-buffer* nil)

  (defvar hd:*presentation-work-buffer* nil)
  (defvar hd:*presentation-work-marker* nil)
  
  
  
  (defun hd:presentation-next-step ()
    (interactive)
    (setf hd:*presentation-previous-buffer* (current-buffer))
    (switch-to-buffer hd:*presentation-code-buffer*)
    (forward-sexp)
    (eval-defun nil)
    (message "."))

  (defun hd:presentation-back-to-previous-buffer ()
    (interactive)
    (switch-to-buffer hd:*presentation-previous-buffer*))

  (global-set-key [f12] 'hd:presentation-next-step)

  (defun hd:presentation-get-message-buffer ()
    (interactive)
    (get-buffer-create "Leining Presentation"))

  (defun hd:presentation-add-message (text &optional erase-old-contents-p)
    (save-excursion
      (switch-to-buffer (hd:presentation-get-message-buffer))
      (when erase-old-contents-p
        (delete-region (point-min) (point-max)))
      (insert-string text)
      (set-buffer-modified-p nil)))

  (defun hd:presentation-2pane-and-message (text &optional erase-old-contents-p)
    (switch-to-buffer (hd:presentation-get-message-buffer))
    (delete-other-windows)
    (hd:presentation-add-message text erase-old-contents-p)
    (end-of-buffer)
    (split-window-vertically (max 4 (1+ (count-lines (point-min) (point-max)))))
    (other-window 1))
  
  ;; end of the presentation code
  )


;; Start
(progn
  (switch-to-buffer (hd:presentation-get-message-buffer))
  (delete-other-windows)
  (delete-region (point-min) (point-max))
  (let ((animate-n-steps 30))
    (animate-string
     (concat "\n"
             "\n           _____ __               __ "
             "\n          / ___// /_  ____  _____/ /_"
             "\n          \\__ \\/ __ \\/ __ \\/ ___/ __/"
             "\n         ___/ / / / / /_/ / /  / /_ "
             "\n        /____/_/ /_/\\____/_/   \\__/  "
             "\n"
             "\n            _       __                 __           __  _                __      "
             "\n           (_)___  / /__________  ____/ /_  _______/ /_(_)___  ____     / /_____ "
             "\n          / / __ \\/ __/ ___/ __ \\/ __  / / / / ___/ __/ / __ \\/ __ \\   / __/ __ \\ "
             "\n         / / / / / /_/ /  / /_/ / /_/ / /_/ / /__/ /_/ / /_/ / / / /  / /_/ /_/ /"
             "\n        /_/_/ /_/\\__/_/   \\____/\\__,_/\\__,_/\\___/\\__/_/\\____/_/ /_/   \\__/\\____/ "
             "\n"
             "\n            __         _       _"
             "\n           / /   ___  (_)___  (_)___  ____ ____  ____ "
             "\n          / /   / _ \\/ / __ \\/ / __ \\/ __ `/ _ \\/ __ \\ "
             "\n         / /___/  __/ / / / / / / / / /_/ /  __/ / / /"
             "\n        /_____/\\___/_/_/ /_/_/_/ /_/\\__, /\\___/_/ /_/ "
             "\n                                   /____/             "
             "\n\n          2010-01-26\n\n          Holger Durer")
     10 10)))


(progn
  (hd:presentation-2pane-and-message "Github page at http://github.com/technomancy/leiningen/\n" t)
  (browse-url "http://github.com/technomancy/leiningen/"))

(progn
  (hd:presentation-add-message "\n That page explains about the installation options."))

(progn
    (hd:presentation-2pane-and-message "Get the binary:\n" t)
    (shell)
    (end-of-buffer)
    (insert-string "cd; test ! -d bbin && mkdir bbin")
    (comint-send-input)
    (sit-for 2)
    (insert-string "wget -O bbin/lein http://github.com/technomancy/leiningen/raw/stable/bin/lein"))

(progn
    (hd:presentation-2pane-and-message "Get the binary:\n" t)
    (shell)
    (comint-send-input)
    (sit-for 4)
    (insert-string "chmod a+x bbin/lein")
    (comint-send-input))

(progn
    (hd:presentation-2pane-and-message "and the jar files it needs:")
    (shell)
    (end-of-buffer)
    (insert-string "~/bbin/lein self-install")
    (comint-send-input))


(progn
  (switch-to-buffer (hd:presentation-get-message-buffer))
  (delete-other-windows)
  (hd:presentation-add-message "That's all for the installation.\n\n\nNow, let's use it!" t))


(progn
    (hd:presentation-2pane-and-message "So let's create a project:" t)
    (shell)
    (end-of-buffer)
    (comint-bol)
    (kill-line)
    (dotimes (cnt 50)
      (insert-string "\n")))

(progn
    (hd:presentation-2pane-and-message "Create a directory" t)
    (shell)
    (end-of-buffer)
    (insert-string "cd; test -d demoproject && rm -rf demoproject")
    (comint-send-input)
    (insert-string "mkdir demoproject; cd demoproject"))

(progn
    (hd:presentation-2pane-and-message "")
    (shell)
    (end-of-buffer)
    (comint-send-input))

(progn
    (hd:presentation-2pane-and-message "Create a directory for our code..." t)
    (shell)
    (end-of-buffer)
    (insert-string "mkdir src")
    (comint-send-input))

(progn
  (hd:presentation-2pane-and-message "... and edit the code (src/main.clj) ..." t)
  (find-file (expand-file-name "~/demoproject/src/main.clj"))
  (delete-region (point-min) (point-max))
  (setf hd:*presentation-work-buffer* (current-buffer)))

(progn
  (hd:presentation-2pane-and-message "... put some code in\n\nThe declaration bits" t)
  (switch-to-buffer hd:*presentation-work-buffer*)
  (end-of-buffer)
  (insert-string ";;  A simple hello-world clojure app\n")
  (insert-string "(ns main\n")
  (insert-string "  (:gen-class)\n")
  (insert-string "  (:use [clojure.contrib.command-line\n")
  (insert-string "         :only (with-command-line print-help)]))\n")
  (insert-string "\n")
  (save-buffer))


(progn
  (hd:presentation-2pane-and-message "... and the main function..." t)
  (switch-to-buffer hd:*presentation-work-buffer*)
  (end-of-buffer)
  (insert-string ";; The main program\n")
  (insert-string "(defn -main [& args]\n")
  (insert-string "  (with-command-line args\n")
  (insert-string "    \"Say hello.\"\n")
  (insert-string "    [[name \"User's name rather than 'world'\" \"world\"]\n")
  (insert-string "     [greeting \"Greeting to use rather than 'Hello'\" \"Hello\"]]\n")
  (insert-string "    (println (str greeting \", \" name \"!\"))))\n")
  (save-buffer))


(progn
  (hd:presentation-2pane-and-message "Next, edit the Leiningen project file (project.clj)" t)
  (find-file (expand-file-name "~/demoproject/project.clj"))
  (delete-region (point-min) (point-max))
  (setf hd:*presentation-work-buffer* (current-buffer))11)


(progn
  (hd:presentation-2pane-and-message "The contents is one (defproject) expression:" t)
  (switch-to-buffer hd:*presentation-work-buffer*)
  (end-of-buffer)
  (insert-string ";; Leiningen project description for our hello world app\n")
  (insert-string "(defproject")
  (setf hd:*presentation-work-marker* (point-marker))
  (insert-string ")\n")
  (save-buffer))

(progn
  (hd:presentation-2pane-and-message "... it needs a name:" t)
  (switch-to-buffer hd:*presentation-work-buffer*)
  (goto-char hd:*presentation-work-marker*)
  (insert-string " helloworldapp")
  (setf hd:*presentation-work-marker* (point-marker))
  (save-buffer))

(progn
  (hd:presentation-2pane-and-message "... and a version string:" t)
  (switch-to-buffer hd:*presentation-work-buffer*)
  (goto-char hd:*presentation-work-marker*)
  (insert-string " \"0.1.0-SNAPSHOT\"")
  (setf hd:*presentation-work-marker* (point-marker))
  (save-buffer))

(progn
  (hd:presentation-2pane-and-message "... specify the dependencies" t)
  (switch-to-buffer hd:*presentation-work-buffer*)
  (goto-char hd:*presentation-work-marker*)
  (insert-string "\n")
  (insert-string "  :dependencies [[org.clojure/clojure \"1.1.0-alpha-SNAPSHOT\"]\n")
  (insert-string "                 [org.clojure/clojure-contrib \"1.0-SNAPSHOT\"]]")
  (setf hd:*presentation-work-marker* (point-marker))
  (save-buffer))

(progn
  (hd:presentation-2pane-and-message "... and the main class" t)
  (switch-to-buffer hd:*presentation-work-buffer*)
  (goto-char hd:*presentation-work-marker*)
  (insert-string "\n")
  (insert-string "  :main main")
  (setf hd:*presentation-work-marker* (point-marker))
  (save-buffer))

(progn
  (switch-to-buffer (hd:presentation-get-message-buffer))
  (delete-other-windows)
  (hd:presentation-add-message "\n\n\n\n   That's our work done.\n\n   Now Leiningen has to do the rest:\n" t))

(progn
    (hd:presentation-2pane-and-message "Fetch the dependencies.\n\n (This will automatically create the lib/ and classes/ directories.)" t)
    (shell)
    (end-of-buffer)
    (insert-string "~/bbin/lein deps"))

(progn
    (hd:presentation-2pane-and-message "")
    (shell)
    (end-of-buffer)
    (comint-send-input))

(progn
    (hd:presentation-2pane-and-message "The result:" t)
    (shell)
    (end-of-buffer)
    (insert-string "ls -l ")
    (comint-send-input)
    (sit-for 1)
    (insert-string "ls -l lib/")
    (comint-send-input)
    (sit-for 1)
    (insert-string "ls -l classes/")
    (comint-send-input))

(progn
    (hd:presentation-2pane-and-message "Compile the files:" t)
    (shell)
    (end-of-buffer)
    (insert-string "~/bbin/lein compile")
    (comint-send-input))

(progn
    (hd:presentation-2pane-and-message "The result:" t)
    (shell)
    (end-of-buffer)
    (insert-string "ls -l classes/")
    (comint-send-input))

(progn
    (hd:presentation-2pane-and-message "And build the jar with every dependency included:" t)
    (shell)
    (end-of-buffer)
    (insert-string "~/bbin/lein uberjar")
    (comint-send-input))

(progn
    (hd:presentation-2pane-and-message "This is a working jar file:" t)
    (shell)
    (end-of-buffer)
    (insert-string "java -jar helloworldapp-standalone.jar")
    (comint-send-input))

(progn
    (hd:presentation-2pane-and-message "Which even does a bit of command line parsing:\n\n" t)
    (shell)
    (end-of-buffer)
    (insert-string "java -jar helloworldapp-standalone.jar --help")
    (comint-send-input))

(progn
    (hd:presentation-2pane-and-message "and thus customisable:")
    (shell)
    (end-of-buffer)
    (insert-string "java -jar helloworldapp-standalone.jar --greeting Howdy --name folks")
    (comint-send-input))

(progn
    (hd:presentation-2pane-and-message "Contents of the project's jar file:")
    (shell)
    (end-of-buffer)
    (insert-string "jar tf helloworldapp.jar")
    (comint-send-input))





(progn
    (hd:presentation-2pane-and-message "What else can leiningen do?\nLet's find out:" t)
    (shell)
    (end-of-buffer)
    (insert-string "~/bbin/lein help")
    (comint-send-input))




(progn
  (switch-to-buffer (hd:presentation-get-message-buffer))
  (delete-other-windows)
  (hd:presentation-add-message "\n\n\n\n    The End.\n\n\n" t))

