#encoding: UTF-8
require_relative 'ShieldToUI.rb'

module Deepspace
class ShieldBooster

#
# ──────────────────────────────────────────────────────────── CONSTRUCTORES ─────
#

   def initialize (n, b, u)
      @name  = n
      @boost = b
      @uses  = u
   end

   def self.newCopy (s)
      t = ShieldBooster.new(s.name(), s.boost(), s.uses())
   end

#
# ─────────────────────────────────────────────────────────────── INTERFACES ─────
#

   attr_reader :name, :boost, :uses

   def useIt
      if (@uses > 0)
         @uses -= 1
         return @boost
      else
         return 1.0
      end
   end

   def getUIversion
      t = ShieldToUI.new(self)
   end

   def to_s
      getUIversion.to_s
   end
end
end

