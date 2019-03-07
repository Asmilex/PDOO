#encoding: UTF-8
require_relative 'ShieldToUI.rb'

module Deepspace
   class ShieldBooster
      def initialize (n, b, u)
         @name  = n
         @boost = b
         @uses  = u
      end

      def self.newCopy (s)
         t = ShieldBooster.new(s.name(), s.boost(), s.uses())
      end

      def name
         @name
      end

      def boost
         return @boost
      end

      def uses
         return @uses
      end

      def uselt
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

   end

end

