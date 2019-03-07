#encoding: UTF-8
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


   end

end

